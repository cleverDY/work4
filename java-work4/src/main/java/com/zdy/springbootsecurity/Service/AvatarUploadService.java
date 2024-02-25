package com.zdy.springbootsecurity.Service;

import com.zdy.springbootsecurity.domain.ResponseResult;
import com.zdy.springbootsecurity.exception.ImoocMallException;
import org.springframework.web.multipart.MultipartFile;

public interface AvatarUploadService {
    /**
     * 上传头像方法
     * @param file 上传的头像文件
     * @return 上传成功或失败的结果
     */
    ResponseResult uploadAvatar(MultipartFile file) throws ImoocMallException;
    boolean modifyUserAvatarUrlById(String userId,String avatarUrl);
}
