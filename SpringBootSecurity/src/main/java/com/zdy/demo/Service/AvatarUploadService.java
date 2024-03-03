package com.zdy.demo.Service;

import com.zdy.demo.pojo.ResponseResult;
import com.zdy.demo.exception.ErrorException;
import org.springframework.web.multipart.MultipartFile;

public interface AvatarUploadService {
    /**
     * 上传头像方法
     * @param file 上传的头像文件
     * @return 上传成功或失败的结果
     */
    ResponseResult uploadAvatar(MultipartFile file) throws ErrorException;
    boolean modifyUserAvatarUrlById(String userId,String avatarUrl);
}
