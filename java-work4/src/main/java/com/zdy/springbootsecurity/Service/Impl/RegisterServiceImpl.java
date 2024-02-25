package com.zdy.springbootsecurity.Service.Impl;

import com.zdy.springbootsecurity.Service.RegisterService;
import com.zdy.springbootsecurity.domain.ResponseResult;
import com.zdy.springbootsecurity.domain.User;
import com.zdy.springbootsecurity.exception.ImoocMallException;
import com.zdy.springbootsecurity.exception.ImoocMallExceptionEnum;
import com.zdy.springbootsecurity.mapper.UserMapper;
import com.zdy.springbootsecurity.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

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
            throw new ImoocMallException(ImoocMallExceptionEnum.NAME_EXISTED);
        }
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //将用户的密码进行编码
        String password = passwordEncoder.encode(user.getPassword());
        //将编码后的密码覆盖到用户信息中
        user.setPassword(password.substring(8));
        //将用户信息持久化到数据库中
        Random random = new Random();
        StringBuilder builder = new StringBuilder(18);
        for (int i = 0; i < 18; i++) {
            int randomNumber = random.nextInt(10); // 生成 0 到 9 的随机数字
            builder.append(randomNumber);
        }

        user.setUsername(user.getUsername());
        user.setId(String.valueOf(builder));
        user.setCreatedAt(LocalDate.now());
        int count = userMapper.insertSelective(user);
        if (count == 0) {
            throw new ImoocMallException(ImoocMallExceptionEnum.INSERT_FAILED);
        }
        return new ResponseResult<>(user);
    }
}