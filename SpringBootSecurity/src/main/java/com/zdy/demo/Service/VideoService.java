package com.zdy.demo.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zdy.demo.pojo.Comment;
import com.zdy.demo.pojo.ResponseResult;
import com.zdy.demo.pojo.Video;
import com.zdy.demo.pojo.VideoDTO;

import java.util.List;

public interface VideoService extends IService<Video> {
    ResponseResult<List<VideoDTO>> queryVideoById(String id, int pageSize, int page_num);

    ResponseResult<List<VideoDTO>> queryVideoByTitle(String title, int pageSize, int page_num) ;

    ResponseResult<List<VideoDTO>> rankList(int pageSize, int page_num) ;

    ResponseResult saveComment(Comment comment) throws Exception;

    ResponseResult deleteComment(String comment_id);
}
