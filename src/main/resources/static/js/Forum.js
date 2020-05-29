function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    commentTarget(questionId, 1, content);
}
function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-"+commentId).val();
    commentTarget(commentId,2, content);
}

function incQuestionLike(e) {
    var x=document.getElementById("likeCount").innerHTML;
    x=parseInt(x)+1;
    document.getElementById("likeCount").innerHTML=x;
    var targetId = e.getAttribute("data-id");
    incLike(1, targetId);
}
function incRecommendLike(e) {
    var x=document.getElementById("recommendLike").innerHTML;
    x=parseInt(x)+1;
    document.getElementById("recommendLike").innerHTML=x;
    var targetId = e.getAttribute("data-id");
    incLike(2, targetId);
}
function incCodeLike(e) {
    var x=document.getElementById("codeLike").innerHTML;
    x=parseInt(x)+1;
    document.getElementById("codeLike").innerHTML=x;
    var targetId = e.getAttribute("data-id");
    incLike(3, targetId);
}
function incCampusLike(e) {
    var x=document.getElementById("campusLike").innerHTML;
    x=parseInt(x)+1;
    document.getElementById("campusLike").innerHTML=x;
    var targetId = e.getAttribute("data-id");
    incLike(4, targetId);
}
function incShareLike(e) {
    var x=document.getElementById("shareLike").innerHTML;
    x=parseInt(x)+1;
    document.getElementById("shareLike").innerHTML=x;
    var targetId = e.getAttribute("data-id");
    incLike(5, targetId);
}
function incCommentLike(e) {
    var x=document.getElementById("commentLike").innerHTML;
    x=parseInt(x)+1;
    document.getElementById("commentLike").innerHTML=x;
    var targetId = e.getAttribute("data-id");
    incLike(6, targetId);
}

function incDownLoad(e) {
    var x=document.getElementById("downloadCount").innerHTML;
    x=parseInt(x)+1;
    document.getElementById("downloadCount").innerHTML=x;
    var targetId = e.getAttribute("data-id");
    incLike(7, targetId);
}
function incLike(type,targetId) {
    $.ajax({
        type: "POST",
        url: "/like",
        contentType: 'application/json',
        data: JSON.stringify({
            "id": targetId,
            "type": type
        }),
        success: function (response) {
            if(response.code != 200) {
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
function commentTarget(targetId, type, content) {
    if (!content) {
        alert("不能回复空内容");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code == 200) {
                $("#comment_section").hide();
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


function collapseComment(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-"+id);
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        var subCommentContainer = $("#comment-"+id);
        if (subCommentContainer.children().length != 1) {
            comments.addClass("in");
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left",
                    }).append($("<img/>", {
                        "class" : "media-object img-rounded forum",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html":comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "pull-right",

                    })));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 box"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });

                comments.addClass("in");
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }
    }
}
function openSelectTag() {
    $("#select-tag").show();
}
function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();
    if(previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }
}

