package com.zdy.demo;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
