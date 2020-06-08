package com.example.demo.controller;

import com.example.demo.Model.CodeSolve;
import com.example.demo.dto.PaginationDTO;
import com.example.demo.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CodeController {
    @Autowired
    private CodeService codeService;

    @GetMapping("/code")
    public String index(@RequestParam(name = "search", required = false) String search,
                        @RequestParam(name="page", defaultValue = "1") Integer page,
                        @RequestParam(name="size", defaultValue = "8") Integer size,
                        Model model) {
        PaginationDTO pagination = codeService.list(search, page, size);
        List<CodeSolve> hots = codeService.HotList();
        model.addAttribute("pagination",pagination);
        model.addAttribute("hots", hots);
        model.addAttribute("search", search);
        return "code";
    }
}
