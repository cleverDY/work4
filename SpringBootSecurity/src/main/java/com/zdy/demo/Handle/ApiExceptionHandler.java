package com.zdy.demo.Handle;

import com.zdy.demo.pojo.ResponseResult;
import com.zdy.demo.exception.ErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice//@ControllerAdvice是一个用于声明全局控制器异常处理的注解。当使用@ControllerAdvice注解修饰的类时，它将作为一个全局异常处理器来拦截应用程序中的异常。
public class ApiExceptionHandler {
    //自定义的异常处理类，用于处理全局的异常
    @ExceptionHandler(ErrorException.class)//注解修饰的方法时，它将处理指定类型的异常。
    @ResponseBody
    public ResponseEntity<ResponseResult> handleException(ErrorException ex) {
        ResponseResult response = new ResponseResult(-1, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}