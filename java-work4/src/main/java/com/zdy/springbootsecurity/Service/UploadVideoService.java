package com.zdy.springbootsecurity.Service;

import com.zdy.springbootsecurity.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadVideoService {
    ResponseResult saveVideoFile(MultipartFile file) throws IOException;
}
