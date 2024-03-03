package com.zdy.demo.Service.Impl;

import com.zdy.demo.Service.RegisterService;
import com.zdy.demo.pojo.ResponseResult;
import com.zdy.demo.pojo.User;
import com.zdy.demo.exception.ErrorException;
import com.zdy.demo.exception.ErrorExceptionEnum;
import com.zdy.demo.mapper.UserMapper;
import com.zdy.demo.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseResult register(User user) throws Exception {
        User result = userMapper.selectByName(user.getUsername());
        if (result != null) {
            throw new ErrorException(ErrorExceptionEnum.NAME_EXISTED);
        }
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //将用户的密码进行编码
        String password = passwordEncoder.encode(user.getPassword());
        //将编码后的密码覆盖到用户信息中
        user.setPassword(password.substring(0));
        //将用户信息持久化到数据库中
        user.setUsername(user.getUsername());
        user.setCreatedAt(LocalDate.now());
        int count = userMapper.insert(user);
        if (count == 0) {
            throw new ErrorException(ErrorExceptionEnum.INSERT_FAILED);
        }
        return new ResponseResult<>(user);
    }
}