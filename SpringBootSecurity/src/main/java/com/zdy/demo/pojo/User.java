package com.zdy.demo.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * 用户表(User)实体类
 *
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User implements Serializable {

    private String password;
    private String username;
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    private String avatarUrl;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate deletedAt;

    public User(String username, String finalPswd, List<GrantedAuthority> authorities) {
    }
}
