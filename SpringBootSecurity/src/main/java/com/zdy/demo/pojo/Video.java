package com.zdy.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("video")
public class Video implements Serializable {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;


    private String userId;

    @TableField(exist = false)
    private String icon;

    @TableField(exist = false)
    private String nickName;

    private String title;

    @TableField("`video_path`")
    private String videoPath;

    private Long click;

    private Integer comments;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
