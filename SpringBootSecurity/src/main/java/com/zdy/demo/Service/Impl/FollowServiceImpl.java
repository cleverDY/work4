package com.zdy.demo.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zdy.demo.Service.FollowService;
import com.zdy.demo.pojo.Follow;
import com.zdy.demo.pojo.ResponseResult;
import com.zdy.demo.mapper.FollowMapper;
import com.zdy.demo.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private FollowMapper followMapper;
    @Autowired
    private EntityManager entityManager;
    @Override
    public ResponseResult follow(String to_user_id) {
        Object id = redisCache.getCacheObject("id");
       // System.out.println(id);
        Follow follow=new Follow();
        follow.setFollow_user_id(to_user_id);
        follow.setUser_id((String) id);
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(follow);
        followMapper.insert(follow);
        return new ResponseResult(follow);
    }

    @Override
    public ResponseResult<Page<String>> getListByUserId(String UserId, Integer page_size, Integer page_num) {
        TypedQuery<String> query = entityManager.createQuery(
                "SELECT follow_user_id FROM Follow ul WHERE ul.user_id= :user_id", String.class);
        query.setParameter("user_id", UserId);

        int offset = (page_num - 1) * page_size;
        query.setFirstResult(offset);
        query.setMaxResults(page_size);

        List<String> follows = query.getResultList();
        System.out.println(follows);
        // 获取总数
        TypedQuery<Long> countQuery = entityManager.createQuery(
                "SELECT COUNT(ul) FROM Follow ul WHERE ul.user_id = :user_id", Long.class);
        countQuery.setParameter("user_id", UserId);
        Long totalCount = countQuery.getSingleResult();

        int totalPages = (int) Math.ceil(totalCount / (double) page_size);
        Page<String> list =new PageImpl<>(follows, PageRequest.of(page_num, page_size), totalPages);
        return new ResponseResult<>(list);
    }

    @Override
    public ResponseResult<Page<String>> getFanListByUserId(String UserId, Integer page_size, Integer page_num) {
        TypedQuery<String> query = entityManager.createQuery(
                "SELECT user_id FROM Follow ul WHERE ul.follow_user_id= :follow_user_id", String.class);
        query.setParameter("follow_user_id", UserId);

        int offset = (page_num - 1) * page_size;
        query.setFirstResult(offset);
        query.setMaxResults(page_size);

        List<String> follows = query.getResultList();
        // 获取总数
        TypedQuery<Long> countQuery = entityManager.createQuery(
                "SELECT COUNT(ul) FROM Follow ul WHERE ul.user_id = :user_id", Long.class);
        countQuery.setParameter("user_id", UserId);
        Long totalCount = countQuery.getSingleResult();

        int totalPages = (int) Math.ceil(totalCount / (double) page_size);
        Page<String> list =new PageImpl<>(follows, PageRequest.of(page_num, page_size), totalPages);
        return new ResponseResult<>(list);
    }

    @Override
    public ResponseResult<Page<String>> getFriendList(Integer page_size, Integer page_num) {
        Object UserId = redisCache.getCacheObject("id");
        System.out.println(UserId);
        TypedQuery<String> query = entityManager.createQuery(
                "SELECT follow_user_id FROM Follow ul WHERE ul.user_id= :user_id", String.class);
        query.setParameter("user_id", UserId);

        int offset = (page_num - 1) * page_size;
        query.setFirstResult(offset);
        query.setMaxResults(page_size);

        List<String> follows = query.getResultList();
        List<String> friends=new ArrayList<>();
        for(String follow:follows){
            TypedQuery<String> query2 = entityManager.createQuery(
                    "SELECT follow_user_id FROM Follow ul WHERE ul.user_id= :user_id", String.class);
            query2.setParameter("user_id", follow);

           if (!friends.contains(follow))
           {
               friends.add(follow);
           }
        }
        TypedQuery<Long> countQuery = entityManager.createQuery(
                "SELECT COUNT(ul) FROM Follow ul WHERE ul.user_id = :user_id", Long.class);
        countQuery.setParameter("user_id", UserId);
        Long totalCount = countQuery.getSingleResult();

        int totalPages = (int) Math.ceil(totalCount / (double) page_size);
        Page<String> list =new PageImpl<>(friends, PageRequest.of(page_num, page_size), totalPages);
        return new ResponseResult<>(list);
    }

}
