package com.example.demo.controller;

import com.example.demo.Model.Comment;
import com.example.demo.Model.User;
import com.example.demo.dto.*;
import com.example.demo.exception.CustomizeErrorCode;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DeleteController {
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
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object like(@RequestBody DeleteDTO deleteDTO,
                       HttpServletRequest request) {
        System.out.println(deleteDTO.getId());
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        int type = deleteDTO.getType();
        Long id = deleteDTO.getId();
        if (type == 1) {
            questionService.deleteById(id);
        }
        else if (type == 2) {
            recommendationService.deleteById(id);
        }
        else if (type == 3) {
            codeService.deleteById(id);
        }
        else if (type == 4) {
            campusService.deleteById(id);
        }
        else if (type == 5) {
            shareService.deleteById(id);
        }
        return ResultDTO.okOf();
    }
}
