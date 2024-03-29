package com.zdy.demo.Service.Impl;
import com.zdy.demo.pojo.LoginUser;
import com.zdy.demo.pojo.User;
import com.zdy.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //实现UserDetailsService接口，重写UserDetails方法，自定义用户的信息从数据中查询
    @Override
    public LoginUser loadUserByUsername(
            // 表单提交的用户名
            String username
    ) throws UsernameNotFoundException {
        Map<String, PasswordEncoder> idToPasswordEncoder = new HashMap<>();
        String idForEncode = "bcrypt";
        idToPasswordEncoder.put(idForEncode, new BCryptPasswordEncoder());
        DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder(idForEncode, idToPasswordEncoder);

        // 根据表单提供的账号 查询User对象，并装配角色、权限等信息
        // 1. 从数据库中查询admin对象
        String sql = "SELECT id,password,username FROM mysqlstudy.user WHERE username=?";
        System.out.println(1);
        List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username);
        User admin = list.get(0);

        System.out.println(2);

        return new LoginUser(admin);
    }
}

/* List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // 角色
        authorities.add(new SimpleGrantedAuthority("UPDATE")); // 权限*/
// 3. 将admin和authorities封装在一起
// 加密方式输入
// String finalPswd = "{noop}" + admin.getUserpswd();
// logger.warn(userpswd);
// 采用bcrypt加密方式
/* String finalPswd =  new BCryptPasswordEncoder().encode(admin.getPassword());*/