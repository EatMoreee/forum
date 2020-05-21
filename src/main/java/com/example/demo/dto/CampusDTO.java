package com.example.demo.dto;

import com.example.demo.Model.User;
import lombok.Data;

@Data
public class CampusDTO {
    private Long id;
    private String title;
    private String tag;
    private Long gmtCreate;
    private Long likeCount;
    private Long viewCount;
    private Long commentCount;
    private Long creator;
    private String description;
    private User user;
}
