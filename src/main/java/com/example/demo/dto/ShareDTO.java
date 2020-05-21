package com.example.demo.dto;

import com.example.demo.Model.User;
import lombok.Data;

import java.util.List;

@Data
public class ShareDTO {
    private Long id;
    private String title;
    private String tag;
    private Long gmtCreate;
    private Long creator;
    private Long viewCount;
    private Long downloadCount;
    private Long likeCount;
    private Long commentCount;
    private String description;
    private User user;

}
