package com.example.demo.controller;

import com.example.demo.cache.SolutionTagCache;
import com.example.demo.cache.TagCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SolutionController {
    @GetMapping("/solution")
    public String publish(Model model) {
        model.addAttribute("tags", SolutionTagCache.get());
        return "solution";
    }
}
