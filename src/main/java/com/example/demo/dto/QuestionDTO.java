package com.example.demo.dto;

import com.example.demo.Model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private long id;
    private String title;
    private String description;
    private String tag;
    private long gmtCreate;
    private long gmtModified;
    private long creator;
    private long commentCount;
    private long viewCount;
    private long likeCount;
    private User user;
}
