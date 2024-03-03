package com.zdy.demo.Service;

import com.zdy.demo.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment save(Comment comment);
    void delete(Comment comment);

}
