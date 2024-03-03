package com.zdy.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@TableName("video")
public class VideoDTO implements Serializable {
    private String user_id;
    private String title;
    private Long click;
    private Integer comments;
    private LocalDateTime create_time;
    private LocalDateTime update_time;

    public VideoDTO(Video video){
        this.create_time=video.getCreateTime();
        this.user_id=video.getUserId();
        this.title = video.getTitle();
        this.click = video.getClick();
        this.comments = video.getComments();
    }
    public VideoDTO(String title, Long click) {
        this.title = title;
        this.click = click;
    }
    public VideoDTO() {
        // 无参构造函数
    }



}
