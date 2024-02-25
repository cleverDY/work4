package com.zdy.springbootsecurity.Service.Impl;

import com.zdy.springbootsecurity.Service.AvatarUploadService;
import com.zdy.springbootsecurity.domain.LoginUser;
import com.zdy.springbootsecurity.domain.ResponseResult;
import com.zdy.springbootsecurity.domain.User;
import com.zdy.springbootsecurity.exception.ImoocMallException;
import com.zdy.springbootsecurity.exception.ImoocMallExceptionEnum;
import com.zdy.springbootsecurity.mapper.UserMapper;
import com.zdy.springbootsecurity.util.FileUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
@Service
public class AvatarUploadImpl implements AvatarUploadService {
    @Autowired
    UserMapper userMapper;

    @Override
    public ResponseResult uploadAvatar(MultipartFile file) throws ImoocMallException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser user = (LoginUser)authentication.getPrincipal();
        //获取用户信息
        String username = user.getUsername();

        // 2. 文件校验（可根据实际需求添加，如文件大小、类型等）
        if (file.isEmpty()) {
            return new ResponseResult(8881, "文件不能为空");
        }
        //3.限制文件大小不超过5MB
        if (file.getSize() > 5 * 1024 * 1024) {
            return new ResponseResult(8882, "文件大小不能超过5MB");
        }
        // 3. 文件存储逻辑
        // 获取当前项目的运行目录
        String currentProjectPath = System.getProperty("user.dir");

        // 构建最终的存储路径，将文件保存在项目目录下的doc/file中
        String storageDirectoryPath = currentProjectPath + File.separator + "doc" + File.separator + "file";

        // 创建File对象，代表存储目录
        File storageDirectory = new File(storageDirectoryPath);

        // 检查目录是否存在，不存在则创建
        if (!storageDirectory.exists()) {
            boolean wasSuccessful = storageDirectory.mkdirs(); // mkdirs会创建多级目录
            if (!wasSuccessful) {
                // 如果目录创建失败，可以返回一个错误响应
                throw new ImoocMallException(ImoocMallExceptionEnum.FILE_UPLOAD_FAILED);
            }
        }

        // 生成最终的文件存储路径
        String finalStoragePath = storageDirectoryPath + File.separator + file.getOriginalFilename();

        try {
            // 存储文件到指定位置
            file.transferTo(new File(finalStoragePath));
            //保存用户头像url
            boolean status = modifyUserAvatarUrlById(user.getUser().getId(), finalStoragePath);
            if(!status){
                throw new RuntimeException("修改用户头像失败");
            }
            // 返回上传成功的信息
            return new ResponseResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ImoocMallException(ImoocMallExceptionEnum.FILE_UPLOAD_FAILED);
        }
    }
    @Override
    public boolean modifyUserAvatarUrlById(String userId, String avatarUrl) {
        if (userId == null || userId.trim().isEmpty() || avatarUrl == null || avatarUrl.trim().isEmpty()) {
            return false;
        }
        int affectedRows = userMapper.modifyUserAvatarUrlById(userId, avatarUrl);
        return affectedRows > 0;
    }
}
