package com.zdy.demo;


import com.zdy.demo.pojo.User;
import com.zdy.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

