package com.zdy.demo.exception;

public enum ErrorExceptionEnum {
    NAME_EXISTED(10000,"用户名存在"),
    INSERT_FAILED(10001,"插入失败"),
    NEED_PASSWORD(10003, "请输入密码"),
    NEED_USER_NAME(10004,"请输入名字"),
    PASSWORD_TOO_SHORT(10005,"密码太短"),
    WRONG_PASSWORD(10006, "密码错误"),
    USER_NULL(10007,"用户不存在"),
    FILE_UPLOAD_FAILED(10008, "上传失败");

    private String message;//具体错误消息
    private Integer Code;//属于哪个模块下的操作失败code

    ErrorExceptionEnum(Integer code, String message) {
        this.message = message;
        Code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }
}
