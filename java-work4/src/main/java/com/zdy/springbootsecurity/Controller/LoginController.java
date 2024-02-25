package com.zdy.springbootsecurity.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zdy.springbootsecurity.Service.*;
import com.zdy.springbootsecurity.domain.*;
import com.zdy.springbootsecurity.entity.UserLikeRepository;
import com.zdy.springbootsecurity.exception.ImoocMallException;
import com.zdy.springbootsecurity.exception.ImoocMallExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : k
 * @Date : 2022/3/23
 * @Desc :
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private InfoService infoService;
    @Autowired
    private AvatarUploadService avatarUploadService;
    @Autowired
    private UploadVideoService uploadVideoService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private LikedService likedService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        return loginService.login(user);
    }

    @PostMapping("/user/register")
    public ResponseResult register(@RequestBody User user) throws Exception {
        return registerService.register(user);
    }
    @GetMapping("/user/info")
    public ResponseResult Info(@RequestParam("user_id") String id,HttpServletRequest request) throws Exception {
        return infoService.Info(id, request);
    }
    //需要写的接口
    @PutMapping("/user/avatar/upload")
    public ResponseResult uploadAvatar(@RequestParam("file") MultipartFile file) throws ImoocMallException {
        return avatarUploadService.uploadAvatar(file);
    }
    @PostMapping("/video/publish")
    public ResponseResult<String> uploadVideo(@RequestParam("file") MultipartFile video) throws IOException {
        return uploadVideoService.saveVideoFile(video);
    }
    @GetMapping("/of/user")
    public ResponseResult<List<VideoDTO>>queryVideoByUserId(
            @RequestParam("page_num")Integer page_num,
            @RequestParam("page_size")Integer page_size,
            @RequestParam("id")  String id) {
        return videoService.queryMyVideo(id,page_size,page_num);
    }
    @PostMapping("/video/search")
    public ResponseResult<List<VideoDTO>>queryVideoByTittle(
            @RequestParam("page_num")Integer page_num,
            @RequestParam("page_size")Integer page_size,
            @RequestParam("title")String title) {
        return videoService.queryVideoByTitle(title,page_size,page_num);
    }
    @GetMapping("/video/popular")
    public ResponseResult<List<VideoDTO>> opulerRankList(
            @RequestParam("page_num")Integer page_num,
            @RequestParam("page_size")Integer page_size) {
        return videoService.ranklist(page_size,page_num);
    }
    @PostMapping("/like/action")
    public ResponseResult likeAction(
            @RequestParam("video_id")String video_id,
            @RequestParam("comment_id")String comment_id,
            @RequestParam("action_type")Integer action_type) {
        // 构造UserLike对象
        UserLike userLike = new UserLike(comment_id,video_id,action_type);
        // 调用likedService.save()来保存点赞记录
        return likedService.save(userLike);
    }
    @GetMapping("/like/list")
    public ResponseResult likeList(
            @RequestParam("user_id")String user_id,
            @RequestParam("page_size")Integer page_size,
            @RequestParam("page_num")Integer page_num) {
        // 调用likedService.save()来保存点赞记录
        return  likedService.getLikedListByLikedPostId(user_id,page_size,page_num);
    }

}

