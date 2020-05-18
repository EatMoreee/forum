package com.example.demo.controller;

import com.example.demo.cache.SharingTagCache;
import com.example.demo.cache.TagCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SharingController {
    @GetMapping("/sharing")
    public String publish(Model model) {
        model.addAttribute("tags", SharingTagCache.get());
        return "sharing";
    }
}
