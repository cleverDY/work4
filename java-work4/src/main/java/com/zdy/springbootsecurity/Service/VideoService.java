package com.zdy.springbootsecurity.Service;

import com.zdy.springbootsecurity.domain.ResponseResult;
import com.zdy.springbootsecurity.domain.VideoDTO;

import java.io.IOException;
import java.util.List;

public interface VideoService {
    ResponseResult<List<VideoDTO>> queryMyVideo(String id, int pageSize, int page_num);

    ResponseResult<List<VideoDTO>> queryVideoByTitle(String title, int pageSize, int page_num) ;

    ResponseResult<List<VideoDTO>> ranklist(int pageSize, int page_num) ;
}
