package com.example.demo.controller;

import com.example.demo.Model.Share;
import com.example.demo.dto.PaginationDTO;
import com.example.demo.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ShareController {
    @Autowired
    private ShareService shareService;

    @GetMapping("/share")
    public String index(@RequestParam(name="page", defaultValue = "1") Integer page,
                        @RequestParam(name="size", defaultValue = "5") Integer size,
                        Model model) {
        PaginationDTO pagination = shareService.list(page, size);
        List<Share> hots = shareService.HotList();
        model.addAttribute("pagination",pagination);
        model.addAttribute("hots", hots);
        return "share";
    }
}
