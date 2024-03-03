package com.zdy.demo.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.jdbc.Null;
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {
    /**
     * 状态码
     */
    /*private Integer code;
    private Base base;
    private Data data;

    private T token;

    public ResponseResult(Integer code, String msg) {
        this.base = new Base(code, msg);
    }
    public ResponseResult(Integer code, String msg,T token) {
        this.base = new Base(code, msg);
        this.token= token;
    }

    public  ResponseResult(Integer code, String msg,User user) {
        this.base = new Base(code, msg);
        this.data = new Data(user.getId(),user.getUsername(),user.getAvatarUrl(),user.getCreatedAt(),user.getUpdatedAt(),user.getDeletedAt());
    }

    public  ResponseResult(Integer code, String msg,User user,T token) {
        this.base = new Base(code, msg);
        this.data = new Data(user.getId(),user.getUsername(),user.getAvatarUrl(),
        user.getCreatedAt(),user.getUpdatedAt(),user.getDeletedAt());
        this.token=token;
    }
    public ResponseResult(Data data) {
        this.code = 200;
        this.data = (Data) data.getItems();
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public T getToken() {
        return this.token;
    }
    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(10000, "success");
    }

    public static <T> ResponseResult<T> success(User user,T t){
        return new ResponseResult<>(10000, "success",user,t);
    }
    public static <T> ResponseResult<T> error() {
        return new ResponseResult<>(-1, "false");
    }*/

    private Integer code;

    private String msg;

    private T data;

    private T token;

    public ResponseResult() {
        this.code = 200;
        this.msg = "success";
    }
    public ResponseResult(T data) {
        this.code = 200;
        this.data = data;
    }
    public ResponseResult(Integer code,String errorMsg) {
        this.code = code;
        this.msg = errorMsg;
    }
    public ResponseResult(T data,T token) {
        this.code = 200;
        this.msg = "success";
        this.data= data;
        this.token=token;
    }
    public static ResponseResult<Null> ok() {
        return new ResponseResult<>(200, null, null,null);
    }
}
