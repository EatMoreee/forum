function deleteQuestion(e) {
    var targetId = e.getAttribute("data-id");
    incLike(1, targetId);
}
function deleteRecommend(e) {
    var targetId = e.getAttribute("data-id");
    incLike(2, targetId);
}
function deleteCode(e) {
    var targetId = e.getAttribute("data-id");
    incLike(3, targetId);
}
function deleteCampus(e) {
    var targetId = e.getAttribute("data-id");
    incLike(4, targetId);
}
function deleteShare(e) {
    var targetId = e.getAttribute("data-id");
    incLike(5, targetId);
}

function incLike(type,targetId) {
    $.ajax({
        type: "POST",
        url: "/delete",
        contentType: 'application/json',
        data: JSON.stringify({
            "id": targetId,
            "type": type
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            }
            else {
                if (response.code == 2003) {
                    var isAccept = confirm(response.message);
                    if (isAccept) {
                        window.open("https://github.com/login/oauth/authorize?client_id=8001a245cc2ff9985a75&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }

                }
                else {
                    alert(response.message);
                }
            }
            console.log(response);
        },
        dataType: "json"
    });
}