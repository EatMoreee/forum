package com.example.demo.controller;

import com.example.demo.Model.Question;
import com.example.demo.Model.User;
import com.example.demo.cache.*;
import com.example.demo.dto.PaginationDTO;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish(Model model, @RequestParam(name = "area", defaultValue = "discuss") String area) {
        if ("discuss".equals(area)) {
            model.addAttribute("tags", TagCache.get());
            model.addAttribute("session", "discuss");
            model.addAttribute("sessionName", "讨论区提问");
        }
        if ("recommendation".equals(area)) {
            model.addAttribute("tags", RecommendationTagCache.get());
            model.addAttribute("session", "recommendation");
            model.addAttribute("sessionName", "课程推荐");
        }
        if ("solution".equals(area)) {
            model.addAttribute("tags", SolutionTagCache.get());
            model.addAttribute("session", "solution");
            model.addAttribute("sessionName", "发表题解");
        }
        if ("record".equals(area)) {
            model.addAttribute("tags", RecordTagCache.get());
            model.addAttribute("session", "record");
            model.addAttribute("sessionName", "校园周边分享");
        }
        if ("sharing".equals(area)){
            model.addAttribute("tags", SharingTagCache.get());
            model.addAttribute("session", "sharing");
            model.addAttribute("sessionName", "资源分享");
        }
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "plate",required = false) String plate,
                            @RequestParam(value = "title",required = false) String title,
                            @RequestParam(value = "description",required = false) String description,
                            @RequestParam(value = "tag", required = false) String tag,
                            @RequestParam(value = "id", required = false) Long id,
                            HttpServletRequest request,
                            Model model) {
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        model.addAttribute("tags", TagCache.get());
        model.addAttribute("session",plate);
        model.addAttribute("sessionName", "发起");
        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        String isValid = TagCache.filterIsValid(tag);
        if (StringUtils.isNotBlank(isValid)) {
            model.addAttribute("error", "输入非法标签"+isValid);
            return "publish";
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        if("discuss".equals(plate)) {
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            question.setId(id);
            questionService.createOrUpdate(question);
        }
        else {
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            question.setId(id);
            questionService.createOrUpdate(question);
        }
        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id, Model model) {
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        model.addAttribute("tags",TagCache.get());
        model.addAttribute("session","discuss");
        model.addAttribute("sessionName", "讨论区");
        return "publish";
    }
}
