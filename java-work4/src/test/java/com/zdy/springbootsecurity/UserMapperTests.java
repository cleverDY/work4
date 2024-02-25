package com.zdy.springbootsecurity;


import com.zdy.springbootsecurity.domain.User;
import com.zdy.springbootsecurity.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author : k
 * @Date : 2022/3/23
 * @Desc :
 */
@SpringBootTest
class  UserMapperTests{
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper(){

        User user = userMapper.selectByName("ozline");
        System.out.println(user);
    }

}

