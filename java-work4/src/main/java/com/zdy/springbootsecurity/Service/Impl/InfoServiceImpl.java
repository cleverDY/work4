package com.zdy.springbootsecurity.Service.Impl;

import com.zdy.springbootsecurity.Service.InfoService;
import com.zdy.springbootsecurity.domain.ResponseResult;
import com.zdy.springbootsecurity.domain.User;
import com.zdy.springbootsecurity.exception.ImoocMallException;
import com.zdy.springbootsecurity.exception.ImoocMallExceptionEnum;
import com.zdy.springbootsecurity.mapper.UserMapper;
import com.zdy.springbootsecurity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public ResponseResult Info(String id,HttpServletRequest request) throws ImoocMallException {
        //通过UsernamePasswordAuthenticationToken获取用户名和密码
        User user =userMapper.selectById(id);
        String token = request.getHeader("Authorization");
        if (user != null) {
            Map<String, String> map = new HashMap<>();
            map.put("token", token);
            return new ResponseResult<>(user,map);
        } else {
            // 处理result为空的情况
            throw new ImoocMallException(ImoocMallExceptionEnum.USER_NULL);
        }
    }
}
