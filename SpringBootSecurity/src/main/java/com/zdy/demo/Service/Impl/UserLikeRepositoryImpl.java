package com.zdy.demo.Service.Impl;

import com.zdy.demo.Service.UserLikeRepository;
import com.zdy.demo.pojo.UserLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


public class UserLikeRepositoryImpl implements UserLikeRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public UserLike save(UserLike userLike) {
        entityManager.persist(userLike);
        return userLike;
    }
    @Override
    public Page<UserLike> getLikedListByLikedPostId(String likedPostId, Integer page_size, Integer page_num) {
        TypedQuery<UserLike> query = entityManager.createQuery(
                "SELECT ul FROM UserLike ul WHERE ul.likedPostId= :likedPostId", UserLike.class);
        query.setParameter("likedPostId", likedPostId);

        int offset = (page_num - 1) * page_size;
        query.setFirstResult(offset);
        query.setMaxResults(page_size);

        List<UserLike> likes = query.getResultList();

        // 获取总数
        TypedQuery<Long> countQuery = entityManager.createQuery(
                "SELECT COUNT(ul) FROM UserLike ul WHERE ul.likedPostId = :likedPostId", Long.class);
        countQuery.setParameter("likedPostId", likedPostId);
        Long totalCount = countQuery.getSingleResult();

        int totalPages = (int) Math.ceil(totalCount / (double) page_size);

        return new PageImpl<>(likes, PageRequest.of(page_num, page_size), totalPages);
    }


    @Override
    public void delete(UserLike userLike) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserLike> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<UserLike> findAll() {
        return null;
    }

    @Override
    public List<UserLike> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<UserLike> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<UserLike> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public <S extends UserLike> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<UserLike> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends UserLike> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends UserLike> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<UserLike> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public UserLike getOne(Long aLong) {
        return null;
    }

    @Override
    public UserLike getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends UserLike> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends UserLike> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends UserLike> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends UserLike> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserLike> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UserLike> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends UserLike, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
