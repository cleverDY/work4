package com.zdy.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zdy.demo.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    // 这里不需要声明 selectOne() 方法
}

