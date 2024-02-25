package com.zdy.springbootsecurity.Service;

import com.zdy.springbootsecurity.domain.ResponseResult;
import com.zdy.springbootsecurity.domain.User;

public interface RegisterService {
    ResponseResult register(User user) throws Exception;
}
