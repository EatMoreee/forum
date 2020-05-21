package com.example.demo.controller;

import com.example.demo.dto.PaginationDTO;
import com.example.demo.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CodeController {
    @Autowired
    private CodeService codeService;

    @GetMapping("/code")
    public String index(@RequestParam(name="page", defaultValue = "1") Integer page,
                        @RequestParam(name="size", defaultValue = "5") Integer size,
                        Model model) {
        PaginationDTO pagination = codeService.list(page, size);
        model.addAttribute("pagination",pagination);
        return "code";
    }
}
