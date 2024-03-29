package com.zdy.demo.Controller;

import com.zdy.demo.Service.LikedService;
import com.zdy.demo.Service.VideoService;
import com.zdy.demo.exception.ErrorException;
import com.zdy.demo.pojo.Comment;
import com.zdy.demo.pojo.ResponseResult;
import com.zdy.demo.pojo.UserLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LikeController {
    @Autowired
    private LikedService likedService;
    @Autowired
    private VideoService videoService;
    @PostMapping("/like/action")
    public ResponseResult likeAction(
            @RequestParam("video_id")String video_id,
            @RequestParam("comment_id")String comment_id,
            @RequestParam("action_type")Integer action_type) throws ErrorException {

        // 调用likedService.save()来保存点赞记录
        return likedService.save(video_id,comment_id,action_type);
    }
    @GetMapping("/like/list")
    public ResponseResult likeList(
            @RequestParam("user_id")String user_id,
            @RequestParam("page_size")Integer page_size,
            @RequestParam("page_num")Integer page_num) {
        // 调用likedService.save()来保存点赞记录
        return  likedService.getLikedListByLikedPostId(user_id,page_size,page_num);
    }
    @PostMapping("/comment/publish")
    public ResponseResult likeAction(
            @RequestParam("video_id") String videoId, @RequestParam("content") String content) throws Exception {

        Comment comment=new Comment();
        comment.setVideo_id(Integer.parseInt(videoId));
        comment.setContent(content);
        // 调用likedService.save()来保存点赞记录
        return videoService.saveComment(comment);
    }
    @DeleteMapping("/comment/delete")
    public ResponseResult likeAction(
            @RequestParam("comment_id")String comment_id)  {
        return videoService.deleteComment(comment_id);
    }
}
