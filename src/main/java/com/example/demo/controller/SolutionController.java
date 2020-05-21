package com.example.demo.controller;

import com.example.demo.cache.SolutionTagCache;
import com.example.demo.cache.TagCache;
import com.example.demo.dto.CodeSolveDTO;
import com.example.demo.dto.RecommendationDTO;
import com.example.demo.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SolutionController {
    @Autowired
    private CodeService codeService;

    @GetMapping("/code/{id}")
    public String solution(@PathVariable(name = "id")Long id, Model model) {
        CodeSolveDTO codeSolveDTO = codeService.getById(id);
        List<CodeSolveDTO> relateCode = codeService.selectRelate(codeSolveDTO);
        codeService.incView(id);
        model.addAttribute("solution",codeSolveDTO);
        model.addAttribute("relateCode",relateCode);
        return "solution";
    }
}
