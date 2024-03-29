package com.zdy.demo.Controller;

import com.zdy.demo.Service.UploadVideoService;
import com.zdy.demo.Service.VideoService;
import com.zdy.demo.pojo.ResponseResult;
import com.zdy.demo.pojo.VideoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private UploadVideoService uploadVideoService;
    @Autowired
    private VideoService videoService;
    @PostMapping("/publish")
    public ResponseResult<String> uploadVideo(@RequestParam("file") MultipartFile video,
                                              @RequestParam("title")String title) throws IOException {
        return uploadVideoService.saveVideoFile(video,title);
    }
    @GetMapping("/list")
    public ResponseResult<List<VideoDTO>>queryVideoByUserId(
            @RequestParam("page_num")Integer page_num,
            @RequestParam("page_size")Integer page_size,
            @RequestParam("user_id")  String id) {
        return videoService.queryVideoById(id,page_size,page_num);
    }
    @PostMapping("/search")
    public ResponseResult<List<VideoDTO>>queryVideoByTittle(
            @RequestParam("page_num")Integer page_num,
            @RequestParam("page_size")Integer page_size,
            @RequestParam("title")String title) {
        return videoService.queryVideoByTitle(title,page_size,page_num);
    }
    @GetMapping("/popular")
    public ResponseResult<List<VideoDTO>> popularRankList(
            @RequestParam("page_num")Integer page_num,
            @RequestParam("page_size")Integer page_size) {
        return videoService.rankList(page_size,page_num);
    }
}
