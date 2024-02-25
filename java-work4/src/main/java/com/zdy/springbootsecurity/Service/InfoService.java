package com.zdy.springbootsecurity.Service;

import com.zdy.springbootsecurity.domain.ResponseResult;
import com.zdy.springbootsecurity.domain.User;
import com.zdy.springbootsecurity.exception.ImoocMallException;

import javax.servlet.http.HttpServletRequest;

public interface InfoService {
    ResponseResult Info(String id, HttpServletRequest request) throws ImoocMallException;
}
