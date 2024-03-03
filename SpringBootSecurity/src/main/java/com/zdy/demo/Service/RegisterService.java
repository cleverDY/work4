package com.zdy.demo.Service;

import com.zdy.demo.pojo.ResponseResult;
import com.zdy.demo.pojo.User;

public interface RegisterService {
    ResponseResult register(User user) throws Exception;
}
