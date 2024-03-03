package com.zdy.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zdy.demo.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper  extends BaseMapper<User> {
    @Select("SELECT * from mysqlstudy.User where username=#{username}")
    User selectByName(String username);
    @Select("SELECT * from mysqlstudy.user where id=#{id}")
    User selectById(String id);
    @Insert("INSERT INTO mysqlstudy.user (username, password, createdAt) VALUES (#{username}, #{password}, #{createdAt})")
    int insertSelective(User user);
    @Select("SELECT * FROM mysqlstudy.user WHERE username = #{userName} AND password = #{password}")
    User selectLogin(@Param("userName") String userName, @Param("password") String password);
    @Update("UPDATE mysqlstudy.user SET avatarUrl = #{avatarUrl} WHERE id = #{userId}")
    int modifyUserAvatarUrlById(@Param("userId") String userId, @Param("avatarUrl") String avatarUrl);
    @Insert("INSERT INTO mysqlstudy.video (title,video_path,user_id ) VALUES (#{title}, #{video_path},#{user_id})")
    void saveVideo( String title, String video_path,String user_id);
}

