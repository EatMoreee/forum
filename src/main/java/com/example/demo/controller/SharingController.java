package com.example.demo.controller;

import com.example.demo.cache.SharingTagCache;
import com.example.demo.cache.TagCache;
import com.example.demo.dto.CampusDTO;
import com.example.demo.dto.ShareDTO;
import com.example.demo.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SharingController {
    @Autowired
    private ShareService shareService;

    @GetMapping("/share/{id}")
    public String publish(@PathVariable(name = "id") Long id, Model model) {
        ShareDTO shareDTO = shareService.getById(id);
        List<ShareDTO> relateShare = shareService.selectRelate(shareDTO);
        shareService.incView(id);
        model.addAttribute("share",shareDTO);
        model.addAttribute("relateShare",relateShare);
        return "sharing";
    }
}
