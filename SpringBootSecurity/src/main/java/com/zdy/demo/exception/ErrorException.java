package com.zdy.demo.exception;


/**
 * 描述：自定义、统一异常；
 */
public class ErrorException extends Exception {
    private  Integer code;
    private  String message;


    public ErrorException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorException(ErrorExceptionEnum exceptionEnum, Integer code) {
        this(exceptionEnum.getCode(), exceptionEnum.getMessage());
    }

    public ErrorException() {
    }

    public ErrorException(ErrorExceptionEnum imoocMallExceptionEnum) {
        this(imoocMallExceptionEnum.getCode(),imoocMallExceptionEnum.getMessage());
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
