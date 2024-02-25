package com.zdy.springbootsecurity;

import com.zdy.springbootsecurity.domain.User;
import com.zdy.springbootsecurity.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class SpringBootSecurityApplicationTests {

    public static void main(String[] args) {
        String password = "123456";

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(password);

        System.out.println("Original password: " + password);
        System.out.println("Encrypted password: " + encryptedPassword);
    }
}
