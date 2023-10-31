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


    public void unfollow(Long memberId, Long followingId){

        List<Follow> list = entityManager.createQuery("select f from Follow as f where f.memberId=:id and f.followingId=:followingId",Follow.class)
                .setParameter("id" , memberId)
                .setParameter("followingId",followingId)
                .getResultList();
        System.out.println(list.get(0));
        entityManager.remove(list.get(0));
    }
    public void follow(Follow follow){
        entityManager.persist(follow);
    }

    public List<Follow> findFollowById(Long memberId){
        return entityManager.createQuery("select f from Follow as f where f.memberId=:id",Follow.class)
                .setParameter("id" , memberId)
                .getResultList();
    }
}
