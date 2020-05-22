package com.example.demo.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    Question_Not_Found(2001,"您找的问题不在，要不换个试试？"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论进行回复"),
    NO_LOGIN(2003, "未登录，请先登录"),
    SYSTEM_ERROR(2004, "服务器异常，请稍后重试"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"你找的评论不在了"),
    COMMENT_IS_EMPTY(2007, "输入内容不能为空"),
    READ_NOTIFICATION_FAILED(2008, "你无权访问该信息"),
    NOTIFICATION_NOT_FOUND(2009, "您找的消息不存在"),
    RECOMMENDATION_NOT_FOUND(2010,"您找的推荐不存在"),
    SOLUTION_NOT_FOUND(2011,"您找的题解不存在"),
    RECORD_NOT_FOUND(2012,"您找的校园周边分享不存在"),
    SHARE_NOT_FOUND(2013, "你找的资源不存在");


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
