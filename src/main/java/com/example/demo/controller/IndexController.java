package com.example.demo.controller;

import com.example.demo.Model.Question;
import com.example.demo.dto.PaginationDTO;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "search", required = false) String search,
                        @RequestParam(name="page", defaultValue = "1") Integer page,
                        @RequestParam(name="size", defaultValue = "5") Integer size) {
        PaginationDTO pagination = questionService.list(search, page, size);
        List<Question> hotQuestions = questionService.HotList();
        model.addAttribute("pagination",pagination);
        model.addAttribute("hots", hotQuestions);
        return "index";
    }

    @GetMapping("/new")
    public String New() {
        return "new";
    }
}
