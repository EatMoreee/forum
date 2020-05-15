package com.example.demo.dto;

import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private Long outerid;
    private String outerTitle;
    private String typeName;
    private Integer type;
}
