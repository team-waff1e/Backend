package io.github.teamwaff1e.waffle.domain.member.dao;


import io.github.teamwaff1e.waffle.domain.member.entity.Follow;
import io.github.teamwaff1e.waffle.domain.member.entity.Member;
import io.github.teamwaff1e.waffle.global.dao.CrudDao;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class FollowDao {

    private final EntityManager entityManager;


    public void unfollow(Follow follow){
        entityManager.remove(follow);
    }
    public void follow(Follow follow){
        entityManager.persist(follow);
    }

    public List<Follow> findFollowListById(Long memberId){
        return entityManager.createQuery("select f from Follow as f where f.memberId=:id",Follow.class)
                .setParameter("id" , memberId)
                .getResultList();
    }
    public Follow findFollowById(Long memberId, Long followingId){
        return entityManager.createQuery("select f from Follow as f where f.memberId=:id",Follow.class)
                .setParameter("id" , memberId)
                .getResultList().get(0);
    }
}
