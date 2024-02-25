package com.zdy.springbootsecurity.Service;


import com.zdy.springbootsecurity.domain.ResponseResult;
import com.zdy.springbootsecurity.domain.User;

/**
 * @author : k
 * @Date : 2022/3/23
 * @Desc :
 */
public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();

}

