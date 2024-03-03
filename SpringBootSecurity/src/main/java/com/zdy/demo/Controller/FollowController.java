package com.zdy.demo.Controller;

import com.zdy.demo.Service.FollowService;
import com.zdy.demo.pojo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class FollowController {
    @Autowired
    private FollowService followService;
    @PostMapping("/relation/action")
    public ResponseResult relationAction(
            @RequestParam("to_user_id")String to_user_id,
            @RequestParam("action_type")Integer action_type
    ) throws Exception {
        return followService.follow(to_user_id);
    }
    @GetMapping("/following/list")
    public ResponseResult followList(
            @RequestParam("user_id")String user_id,
            @RequestParam("page_size")Integer page_size,
            @RequestParam("page_num")Integer page_num) {
        // 调用likedService.save()来保存点赞记录
        return  followService.getListByUserId(user_id,page_size,page_num);
    }
    @GetMapping("/follower/list")
    public ResponseResult fanList(
            @RequestParam("user_id")String user_id,
            @RequestParam("page_size")Integer page_size,
            @RequestParam("page_num")Integer page_num) {
        // 调用likedService.save()来保存点赞记录
        return  followService.getFanListByUserId(user_id,page_size,page_num);
    }
    @GetMapping("/friends/list")
    public ResponseResult friendList(
            @RequestParam("page_size")Integer page_size,
            @RequestParam("page_num")Integer page_num) {
        // 调用likedService.save()来保存点赞记录
        return  followService.getFriendList(page_size,page_num);
    }
}
