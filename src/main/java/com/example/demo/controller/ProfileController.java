package com.example.demo.controller;

import com.example.demo.Model.User;
import com.example.demo.cache.*;
import com.example.demo.dto.PaginationDTO;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private CodeService codeService;

    @Autowired
    private CampusService campusService;

    @Autowired
    private ShareService shareService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          Model model,
                          @PathVariable(name = "action") String action,
                          @RequestParam(name = "area", defaultValue = "question") String area,
                          @RequestParam(name="page", defaultValue = "1") Integer page,
                          @RequestParam(name="size", defaultValue = "6") Integer size) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) return "redirect:/";
        if ("questions".equals(action)) {
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
            PaginationDTO pagination = null;
            if ("question".equals(area)) {
                pagination = questionService.list(user.getId(), page, size);
            }
            if ("recommend".equals(area)) {
                pagination = recommendationService.list(user.getId(), page, size);
            }
            if ("code".equals(area)) {
                pagination = codeService.list(user.getId(), page, size);
            }
            if ("campus".equals(area)) {
                pagination = campusService.list(user.getId(), page, size);
            }
            if ("share".equals(area)){
                pagination = shareService.list(user.getId(), page, size);
            }
            model.addAttribute("area", area);
            model.addAttribute("pagination", pagination);
        }
        else if ("replies".equals(action)) {
            PaginationDTO paginationDTO = notificationService.list(user.getId(), page, size);
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
            model.addAttribute("pagination", paginationDTO);
        }
        else if ("information".equals(action)) {
            model.addAttribute("section", "information");
            model.addAttribute("sectionName", "个人资料");
            model.addAttribute("user", user);
        }
        return "profile";
    }
}
