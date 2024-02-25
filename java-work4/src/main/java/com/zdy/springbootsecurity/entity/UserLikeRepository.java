package com.zdy.springbootsecurity.entity;

import com.zdy.springbootsecurity.domain.UserLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLikeRepository extends JpaRepository<UserLike, Long> {

    // 保存用户喜欢的内容
    UserLike save(UserLike userLike);
    /*
     * 根据点赞人的id查询点赞列表（即查询这个人都给谁点赞过）
     */
    Page<UserLike> getLikedListByLikedPostId(String likedUserId, Integer page_size,Integer page_num);

}
