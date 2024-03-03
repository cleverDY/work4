package com.zdy.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "follow")
public class Follow implements Serializable {
    @Id
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private String user_id;
    private String follow_user_id;
    private LocalDate create_time;

    // 以下为构造方法、getter 和 setter 方法
    public Follow() {
    }

    public Follow(String userId, String followUserId, LocalDate create_time) {
        this.user_id = userId;
        this.follow_user_id = followUserId;
        this.create_time = create_time;
    }
}
