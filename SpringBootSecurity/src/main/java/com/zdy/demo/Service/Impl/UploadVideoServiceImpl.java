package com.zdy.demo.Service.Impl;

import com.zdy.demo.Service.UploadVideoService;
import com.zdy.demo.pojo.LoginUser;
import com.zdy.demo.pojo.ResponseResult;
import com.zdy.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UploadVideoServiceImpl implements UploadVideoService {
    @Autowired
    UserMapper userMapper;
    @Override
    public ResponseResult saveVideoFile(MultipartFile file,String title) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser user = (LoginUser)authentication.getPrincipal();
        //获取用户信息
        String user_id = user.getUser().getId();
        // 获取当前项目的运行目录
        String currentProjectPath = System.getProperty("user.dir");
        // 获取上传文件的保存目录（可以根据需求自行配置）
        String storageDirectoryPath = currentProjectPath + File.separator + "doc" + File.separator + "video";

        // 创建目标文件对象
        File targetFile = new File(storageDirectoryPath, title);

        // 将上传文件保存到目标文件中
        file.transferTo(targetFile);

        // 调用视频服务的方法，将文件相关信息保存到数据库中
        userMapper.saveVideo(title, targetFile.getAbsolutePath(),user_id);

        // 返回成功的响应结果
        return new ResponseResult(200, "视频上传成功");
    }
    public static String getFileExtension(String fileName) {
        if (fileName == null) {
            return null;
        }
        int extensionIndex = fileName.lastIndexOf(".");

        if (extensionIndex >= 0 && extensionIndex < fileName.length() - 1) {
            return fileName.substring(extensionIndex + 1).toLowerCase();
        } else {
            return "";
        }
    }
}
