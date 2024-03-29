package com.zdy.demo.Service;
import com.zdy.demo.exception.ErrorException;
import com.zdy.demo.pojo.ResponseResult;
import com.zdy.demo.pojo.UserLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LikedService {

    /**
     * 保存点赞记录
     * @param userLike
     * @return
     */
    @Transactional
    ResponseResult save(String videoId, String commentId, Integer type) throws ErrorException;

    /**
     * 批量保存或修改
     * @param list
     */
    List<UserLike> saveAll(List<UserLike> list);


    /**
     * 根据被点赞人的id查询点赞列表（即查询都谁给这个人点赞过）
     * @param likedUserId 被点赞人的id
     * @param pageable
     * @return
     */
    Page<UserLike> getLikedListByLikedUserId(String likedUserId, Pageable pageable);

    /**
     * 根据点赞人的id查询点赞列表（即查询这个人都给谁点赞过）
     * @return
     */
    ResponseResult<Page<UserLike>> getLikedListByLikedPostId(String likedUserId, Integer page_size, Integer page_num);

}
