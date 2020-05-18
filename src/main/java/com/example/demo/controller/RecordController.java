package com.example.demo.controller;

import com.example.demo.cache.RecordTagCache;
import com.example.demo.cache.TagCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecordController {
    @GetMapping("/record")
    public String publish(Model model) {
        model.addAttribute("tags", RecordTagCache.get());
        return "record";
    }
}
