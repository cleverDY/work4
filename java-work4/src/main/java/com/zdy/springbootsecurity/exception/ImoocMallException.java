package com.zdy.springbootsecurity.exception;


/**
 * 描述：自定义、统一异常；
 */
public class ImoocMallException extends Exception {
    private  Integer code;
    private  String message;


    public ImoocMallException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ImoocMallException(ImoocMallExceptionEnum exceptionEnum, Integer code) {
        this(exceptionEnum.getCode(), exceptionEnum.getMessage());
    }

    public ImoocMallException() {
    }

    public ImoocMallException(ImoocMallExceptionEnum imoocMallExceptionEnum) {
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
