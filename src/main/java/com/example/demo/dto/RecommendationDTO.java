package com.example.demo.dto;

import com.example.demo.Model.User;
import lombok.Data;

@Data
public class RecommendationDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Long commentCount;
    private Long viewCount;
    private Long likeCount;
    private User user;
}
