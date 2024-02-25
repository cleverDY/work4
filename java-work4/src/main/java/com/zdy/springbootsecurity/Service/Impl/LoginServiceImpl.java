package com.zdy.springbootsecurity.Service.Impl;

import com.zdy.springbootsecurity.Service.LoginService;
import com.zdy.springbootsecurity.domain.LoginUser;
import com.zdy.springbootsecurity.domain.ResponseResult;
import com.zdy.springbootsecurity.domain.User;
import com.zdy.springbootsecurity.mapper.UserMapper;
import com.zdy.springbootsecurity.util.JwtUtil;
import com.zdy.springbootsecurity.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author : k
 * @Date : 2022/3/23
 * @Desc :
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private UserMapper userMapper;
    @Override
    public ResponseResult login(User user) {
        //通过UsernamePasswordAuthenticationToken获取用户名和密码
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        //AuthenticationManager委托机制对authenticationToken 进行用户认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没有通过，给出对应的提示
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("");
        }
        //如果认证通过，使用user生成jwt  jwt存入ResponseResult 返回
        //如果认证通过，拿到这个当前登录用户信息
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        User user1= userMapper.selectByName(user.getUsername());
        //获取当前用户的userid
        String userid = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        //把完整的用户信息存入redis  userid为key   用户信息为value
        redisCache.setCacheObject("login:" + userid, loginUser);
        return new ResponseResult<>(user1,map);
    }

    @Override
    public ResponseResult logout() {
        return null;
    }

}


