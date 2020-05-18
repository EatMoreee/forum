package com.example.demo.controller;

import com.example.demo.cache.RecommendationTagCache;
import com.example.demo.cache.TagCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecommendationController {
    @GetMapping("/recommendation")
    public String publish(Model model) {
        model.addAttribute("tags", RecommendationTagCache.get());
        return "recommendation";
    }

}
