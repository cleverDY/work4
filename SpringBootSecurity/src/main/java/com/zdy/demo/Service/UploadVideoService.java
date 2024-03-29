package com.zdy.demo.Service;

import com.zdy.demo.pojo.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadVideoService {
    ResponseResult saveVideoFile(MultipartFile file,String title) throws IOException;
}
