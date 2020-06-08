package com.example.demo.controller;

import com.example.demo.Model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {


    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String Login(Model model) {
        model.addAttribute("section", "login");
        return "login";
    }

    @GetMapping("/user/{action}")
    public String user(Model model, @PathVariable(name = "action")String action) {
        model.addAttribute("section", action);
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "password", required = false)String password,
                        HttpServletResponse response,
                        Model model) {
        model.addAttribute("name",name);
        model.addAttribute("password", password);
        model.addAttribute("section", "login");
        if (name == null || name.trim().equals("")) {
            model.addAttribute("error", "用户名不能为空");
            return "login";
        }
        if (password == null || password.trim().equals("")) {
            model.addAttribute("error", "密码不能为空");
            return "login";
        }
        boolean fg = userService.login(name,password);
        if (fg == true) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(name);
            user.setPassword(password);
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }
        model.addAttribute("error","用户名或密码错误");
        return "login";
    }

    @PostMapping("/register")
    public String register(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "password", required = false)String password,
                           HttpServletResponse response,
                           Model model) {
        boolean fg = userService.register(name);
        if (fg == true) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(name);
            user.setPassword(password);
            user.setAvatarUrl("https://uploadxxw.oss-cn-beijing.aliyuncs.com/picture/profile.jpg");
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token",token));
            return "new";
        }
        model.addAttribute("section", "register");
        model.addAttribute("error","用户名重复");
        return "login";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                        HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}
