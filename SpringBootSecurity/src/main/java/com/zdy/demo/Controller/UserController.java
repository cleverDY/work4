package com.zdy.demo.Controller;

import com.zdy.demo.Service.AvatarUploadService;
import com.zdy.demo.Service.LoginService;
import com.zdy.demo.Service.RegisterService;
import com.zdy.demo.pojo.ResponseResult;
import com.zdy.demo.pojo.User;
import com.zdy.demo.exception.ErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")//设置一个基础路径。
public class UserController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private AvatarUploadService avatarUploadService;
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) throws ErrorException {
        return loginService.login(user);
    }

    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user) throws Exception {
        return registerService.register(user);
    }
    @GetMapping("/info")
    public ResponseResult Info(@RequestParam("user_id") String id, HttpServletRequest request) throws Exception {
        return loginService.Info(id, request);
    }
    //需要写的接口
    @PutMapping("/avatar/upload")
    public ResponseResult uploadAvatar(@RequestParam("file") MultipartFile file) throws ErrorException {
        return avatarUploadService.uploadAvatar(file);
    }
}
