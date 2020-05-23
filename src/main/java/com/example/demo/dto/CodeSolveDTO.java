package com.example.demo.dto;

import com.example.demo.Model.User;
import lombok.Data;

@Data
public class CodeSolveDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long createTime;
    private Long viewCount;
    private Long likeCount;
    private Long creator;
    private Integer limitation;
    private User user;
}

