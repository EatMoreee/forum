package com.example.demo.controller;

import com.example.demo.cache.RecordTagCache;
import com.example.demo.cache.TagCache;
import com.example.demo.dto.CampusDTO;
import com.example.demo.dto.CodeSolveDTO;
import com.example.demo.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RecordController {
    @Autowired
    private CampusService campusService;

    @GetMapping("/campus/{id}")
    public String record(@PathVariable(name = "id")Long id, Model model) {
        CampusDTO campusDTO = campusService.getById(id);
        List<CampusDTO> relateCampus = campusService.selectRelate(campusDTO);
        campusService.incView(id);
        model.addAttribute("campus",campusDTO);
        model.addAttribute("relateCampus",relateCampus);
        return "record";
    }
}
