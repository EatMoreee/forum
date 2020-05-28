package com.example.demo.controller;


import com.example.demo.Model.CodeSolve;
import com.example.demo.Model.Comment;
import com.example.demo.Model.User;
import com.example.demo.dto.*;
import com.example.demo.exception.CustomizeErrorCode;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LikeController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private CodeService codeService;

    @Autowired
    private CampusService campusService;

    @Autowired
    private ShareService shareService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public Object like(@RequestBody LikeDTO likeDTO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        int type = likeDTO.getType();
        Long id = likeDTO.getId();
        if (type == 1) {
            questionService.incLike(id);
            QuestionDTO questionDTO = questionService.getById(id);
            userService.addGrade(3, questionDTO.getCreator());
        }
        else if (type == 2) {
            recommendationService.incLike(id);
            RecommendationDTO recommendationDTO = recommendationService.getById(id);
            userService.addGrade(3,recommendationDTO.getCreator());
        }
        else if (type == 3) {
            codeService.incLike(id);
            CodeSolveDTO codeSolveDTO = codeService.getById(id);
            userService.addGrade(3,codeSolveDTO.getCreator());
        }
        else if (type == 4) {
            campusService.incLike(id);
            CampusDTO campusDTO = campusService.getById(id);
            userService.addGrade(3,campusDTO.getCreator());
        }
        else if (type == 5) {
            shareService.incLike(id);
            ShareDTO shareDTO = shareService.getById(id);
            userService.addGrade(3,shareDTO.getCreator());
        }
        else if (type == 6) {
            commentService.incLike(id);
            Comment comment = commentService.getById(id);
            userService.addGrade(3,comment.getCommentator());
        }
        else if (type == 7) {
            shareService.incDownload(id);
        }
        return ResultDTO.okOf();
    }
}
