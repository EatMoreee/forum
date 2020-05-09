package com.example.demo.enums;

public enum CommentTypeEnum {
    QUESTION(1L),
    COMMENT(2L);

    private Long type;

    public static boolean isExist(Long type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if (commentTypeEnum.getType() == type) return true;
        }
        return false;
    }

    public Long getType() {return type;}
    CommentTypeEnum(Long type) {
        this.type = type;
    }
}
