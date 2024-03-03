package com.zdy.demo.Service.Impl;

import com.zdy.demo.Service.CommentRepository;
import com.zdy.demo.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public abstract class CommentRepositoryImpl implements CommentRepository {
    @Autowired
    private EntityManager entityManager;
    @Transactional
    @Override
    public Comment save(Comment comment) {
        entityManager.persist(comment);
        return comment;
    }

    @Override
    public void delete(Comment comment) {
        entityManager.remove(comment);
    }

}
