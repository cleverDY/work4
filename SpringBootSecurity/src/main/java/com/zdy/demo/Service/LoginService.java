package com.zdy.demo.Service;


import com.zdy.demo.exception.ErrorException;
import com.zdy.demo.pojo.ResponseResult;
import com.zdy.demo.pojo.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : k
 * @Date : 2022/3/23
 * @Desc :
 */
public interface LoginService {
    ResponseResult login(User user) throws ErrorException;

    ResponseResult Info(String id, HttpServletRequest request) throws ErrorException;

    ResponseResult logout();

}

