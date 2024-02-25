package com.zdy.springbootsecurity.Service.Impl;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.zdy.springbootsecurity.domain.LoginUser;
import com.zdy.springbootsecurity.domain.User;
import com.zdy.springbootsecurity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author : k
 * @Date : 2022/3/23
 * @Desc :
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;


    //实现UserDetailsService接口，重写UserDetails方法，自定义用户的信息从数据中查询
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //（认证，即校验该用户是否存在）查询用户信息
        //如果没有查询到用户
        User user = userMapper.selectByName(username);
        if (Objects.isNull(user)){
            throw new RuntimeException("用户名或者密码错误");
        }
        //TODO (授权，即查询用户具有哪些权限)查询对应的用户信息
        //把数据封装成UserDetails返回
        return new LoginUser(user);
    }
}

