package com.zdy.demo.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zdy.demo.pojo.Follow;
import com.zdy.demo.pojo.ResponseResult;
import org.springframework.data.domain.Page;

public interface FollowService extends IService<Follow> {
    ResponseResult follow(String to_user_id);
    ResponseResult<Page<String>> getListByUserId(String UserId, Integer page_size, Integer page_num);
    ResponseResult<Page<String>> getFanListByUserId(String UserId, Integer page_size, Integer page_num);
    ResponseResult<Page<String>> getFriendList(Integer page_size, Integer page_num);
}
