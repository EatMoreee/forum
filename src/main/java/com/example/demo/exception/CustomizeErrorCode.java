package com.example.demo.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    Question_Not_Found(2001,"您找的问题不在，要不换个试试？"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论进行回复"),
    NO_LOGIN(2003, "未登录无法评论，请先登录"),
    SYSTEM_ERROR(2004, "服务器异常，请稍后重试"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"你找的评论不在了"),
    COMMENT_IS_EMPTY(2007, "输入内容不能为空");


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
