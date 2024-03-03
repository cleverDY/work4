package com.zdy.demo.pojo;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int video_id;

    private String userId;

    private Long parentId;


    private String content;

    @Column(name = "create_time",updatable = false)

    private LocalDate createdAt;
}

