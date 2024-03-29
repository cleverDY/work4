package com.zdy.demo.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zdy.demo.pojo.*;
import com.zdy.demo.Service.CommentRepository;
import com.zdy.demo.mapper.CommentMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zdy.demo.Service.VideoService;
import com.zdy.demo.mapper.VideoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

import redis.clients.jedis.Tuple;

import javax.transaction.Transactional;

@Slf4j
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentRepository commentRepository;
    private static final String REDIS_HOST = "localhost";
    private static final int REDIS_PORT = 6379;
    private static final String RANK_KEY = "rank";

    @Override
    public ResponseResult<List<VideoDTO>> queryVideoById(String id, int pageSize, int page_num) {
        List<VideoDTO> videoDTOList = new ArrayList<>(); // 存储所有页面的视频列表
        for (int pageNum = 0; pageNum <= page_num; pageNum++) {
            Page<Video> page = new Page<>(pageNum, pageSize);
            IPage<Video> videoPage = videoMapper.selectPage(page, new QueryWrapper<Video>()
                    .eq("user_id", id)); // 使用MyBatis-Plus库来进行查询
            // 获取当前页的视频列表
            List<Video> videoList = videoPage.getRecords();
            List<VideoDTO> pageVideoDTOList = videoList.stream()
                    .map(VideoDTO::new)
                    .collect(Collectors.toList());
            //System.out.println(pageVideoDTOList);

            videoDTOList.addAll(pageVideoDTOList); // 将当前页面的视频列表添加到总列表中
        }

        return new ResponseResult<>(videoDTOList);
    }

    @Override
    public ResponseResult<List<VideoDTO>> queryVideoByTitle(String title, int pageSize, int page_num) {
        List<VideoDTO> videoDTOList = new ArrayList<>(); // 存储所有页面的视频列表
       // System.out.println(title);
        for (int pageNum = 0; pageNum <=page_num; pageNum++) {
            Page<Video> page = new Page<>(pageNum, pageSize);
            IPage<Video> videoPage = videoMapper.selectPage(page, new QueryWrapper<Video>()
                    .eq("title", title)); // 根据需要修改查询条件
            // 获取当前页的视频列表
            List<Video> videoList = videoPage.getRecords();
            List<VideoDTO> pageVideoDTOList = videoList.stream()
                    .map(VideoDTO::new)
                    .collect(Collectors.toList());
            videoDTOList.addAll(pageVideoDTOList); // 将当前页面的视频列表添加到总列表中
            System.out.println(pageVideoDTOList);
        }

        return new ResponseResult<>(videoDTOList);
    }

    @Override
    public ResponseResult<List<VideoDTO>> rankList(int pageSize, int page_num) {
        Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);
        jedis.select(1);
        List<VideoDTO> videos = getVideosFromDatabase(); // 从数据库中获取视频数据
        addVideosToRedis(jedis, videos); // 将视频数据存储到 Redis 中
        Set<Tuple> rankList = jedis.zrevrangeWithScores("rank", page_num * pageSize, (page_num + 1) * pageSize - 1);

        List<VideoDTO> topVideos = rankList.stream()
                .map(tuple -> new VideoDTO(tuple.getElement(), (long) tuple.getScore()))
                .collect(Collectors.toList());

        return new ResponseResult<>(topVideos);
    }
    public List<VideoDTO> getVideosFromDatabase() {
        // 构造查询条件
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        // 这里假设 VideoEntity 类中有 name 和 clickRate 字段，具体字段名请根据实际情况调整
        wrapper.select("title", "click");
        // 执行查询
        List<Video> videos = videoMapper.selectList(wrapper);
        // 将查询结果转换为 VideoDTO 对象并返回
        return convertToDTO(videos);
    }
    private List<VideoDTO> convertToDTO(List<Video> videos) {
        List<VideoDTO> dtos = new ArrayList<>();
        for (Video video : videos) {
            VideoDTO dto = new VideoDTO();
            dto.setTitle(video.getTitle());
            dto.setClick(video.getClick());
            dtos.add(dto);
        }
        return dtos;
    }
    public void addVideosToRedis(Jedis jedis, List<VideoDTO> videos) {
        for (VideoDTO video : videos) {
            jedis.zadd(RANK_KEY, video.getClick(), video.getTitle());
        }
    }
    @Transactional
    @Override
    //目前只是写了一级评论，没有写二级评论
    public ResponseResult saveComment(Comment comment) throws Exception {
        LoginUser principal = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = principal.getUser();
        System.out.println(comment.getVideo_id());
        if (comment.getVideo_id() == 0)
        {
            throw new Exception("getVideo_id() == null");
        }
        if ( StringUtils.isEmpty(comment.getContent())) {
            throw new Exception("null");
        }

        comment.setUserId(user.getId());
        /*comment.setCreatedAt(System.currentTimeMillis());*/
        LocalDateTime createdAt = LocalDateTime.ofEpochSecond(System.currentTimeMillis() / 1000, 0, ZoneOffset.UTC);
        comment.setCreatedAt(LocalDate.from(createdAt));
        comment.setParentId(0L);
        Video video = videoMapper.selectById(comment.getVideo_id());
        UpdateWrapper<Video> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", comment.getVideo_id())
                .set("comments", video.getComments()+1);//更新操作
         videoMapper.update(null, updateWrapper);
        Comment save = commentRepository.save(comment);
        //更新video表里面comment的值
        //System.out.println(video.getComments());
        return new  ResponseResult<>(save);
    }

    @Override
    public ResponseResult deleteComment(String comment_id) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", comment_id);
        int deleteCount = commentMapper.delete(queryWrapper);
        if (deleteCount > 0) {
            return new ResponseResult("Success");
        } else {
            return new ResponseResult("Error");
        }
    }

}
/*int from = (page_num - 1) * pageSize;  ;  // 计算起始位置

        SearchRequest searchRequest = new SearchRequest("videos");  // 指定索引名称
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery("title", tittle));  // 构建查询条件，根据标题匹配
        sourceBuilder.from(from);  // 设置查询起始位置
        sourceBuilder.size(pageSize);  // 设置每页数量
        sourceBuilder.sort("title.keyword", SortOrder.ASC);  // 按标题升序排序

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");  // 设置标题高亮显示
        sourceBuilder.highlighter(highlightBuilder);
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse ;
        try {
            searchResponse = restClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("查询失败", e);
            throw new RuntimeException("查询失败");
        }
        List<VideoDTO> videoList = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            VideoDTO videoDTO = new VideoDTO();
            videoDTO.setTitle(hit.getSourceAsMap().get("title").toString());  // 设置标题
            // 其他字段的映射和设置
            videoList.add(videoDTO);
        }

        return new ResponseResult<>(videoList);
    }*/
