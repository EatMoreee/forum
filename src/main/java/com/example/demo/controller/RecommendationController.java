package com.example.demo.controller;


import com.example.demo.dto.RecommendationDTO;
import com.example.demo.enums.CommentTypeEnum;
import com.example.demo.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/recommend/{id}")
    public String recommendation(@PathVariable(name = "id") Long id, Model model) {
        recommendationService.incView(id);
        RecommendationDTO recommendationDTO = recommendationService.getById(id);
        List<RecommendationDTO> relateRecommend = recommendationService.selectRelate(recommendationDTO);
        model.addAttribute("recommendation",recommendationDTO);
        model.addAttribute("relateRecommend",relateRecommend);
        return "recommendation";
    }

}
