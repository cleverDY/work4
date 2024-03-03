package com.zdy.demo.Service.Impl;
import com.zdy.demo.Service.LikedService;
import com.zdy.demo.pojo.ResponseResult;
import com.zdy.demo.pojo.UserLike;
import com.zdy.demo.Service.UserLikeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class LikedServiceImpl implements LikedService {

    @Autowired
    UserLikeRepository userLikeRepository;
    @Override
    @Transactional
    public ResponseResult save(UserLike userLike) {
        UserLike save = userLikeRepository.save(userLike);
        return new  ResponseResult<>(save);
    }

    @Override
    public List<UserLike> saveAll(List<UserLike> list) {
        return null;
    }

    @Override
    public Page<UserLike> getLikedListByLikedUserId(String likedUserId, Pageable pageable) {
        return null;
    }
    @Override
    public ResponseResult<Page<UserLike>> getLikedListByLikedPostId(String likedUserId, Integer page_size, Integer page_num) {
        Page<UserLike> likedListByLikedPostId = userLikeRepository.getLikedListByLikedPostId(likedUserId, page_size, page_num);
        return new ResponseResult<>(likedListByLikedPostId);
    }
}
