package com.example.demo.controller;

import com.example.demo.Model.User;
import com.example.demo.cache.*;
import com.example.demo.dto.PaginationDTO;
import com.example.demo.exception.FileException;
import com.example.demo.exception.ImgException;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          Model model,
                          @PathVariable(name = "action") String action,
                          @RequestParam(name = "area", defaultValue = "question") String area,
                          @RequestParam(name="page", defaultValue = "1") Integer page,
                          @RequestParam(name="size", defaultValue = "9") Integer size) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) return "redirect:/";
        model.addAttribute("area", area);
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
        }
        return "profile";
    }

    @PostMapping("/image")
    @RequestMapping("image")
    public String changeImg(HttpServletRequest request,Model model) {
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multiRequest.getFile("uploadImg");
        String url = null;
        if (file != null) {
            try {
                url = fileService.upDateImg(file);
            } catch (ImgException e) {
                e.printStackTrace();
            }
        }
        User user = (User) request.getSession().getAttribute("user");
        if (url != null) userService.updateImg(user, url);
        model.addAttribute("section", "information");
        model.addAttribute("sectionName", "个人资料");
        return "redirect:/profile/information";
    }

    @PostMapping("/password")
    public String changePassword(@RequestParam(value = "oldPassword")String oldPassword,
                                 @RequestParam(value = "newPassword")String newPassword,
                                 HttpServletRequest request,
                                 Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (oldPassword.equals(user.getPassword())) {
            userService.updatePassword(user, newPassword);
        }
        else {
            model.addAttribute("error", "密码错误");
        }
        model.addAttribute("section", "information");
        model.addAttribute("sectionName", "个人资料");
        return "redirect:/profile/information";
    }
}
