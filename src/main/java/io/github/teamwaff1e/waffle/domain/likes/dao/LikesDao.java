package io.github.teamwaff1e.waffle.domain.likes.dao;

import io.github.teamwaff1e.waffle.domain.likes.dto.request.LikesRequestDto;
import io.github.teamwaff1e.waffle.domain.likes.entity.Likes;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class LikesDao {

    private final EntityManager entityManager;

    public void like(LikesRequestDto likesRequestDto){
        Likes likes = Likes.builder()
                .memberId(likesRequestDto.getMemberId())
                .waffleId(likesRequestDto.getWaffleId())
                .build();

        Likes attachedLikes = entityManager.merge(likes);
        entityManager.persist(attachedLikes);
//        entityManager.persist(likes);
    }
    public void unlike(LikesRequestDto likesRequestDto){
        Likes likes = Likes.builder()
                .memberId(likesRequestDto.getMemberId())
                .waffleId(likesRequestDto.getWaffleId())
                .build();

        Likes attachedLikes = entityManager.merge(likes);
        entityManager.remove(attachedLikes);
//        entityManager.remove(likes);
    }

    public Optional<Likes> findLikesById(LikesRequestDto likesRequestDto){
        return entityManager.createQuery("select l from Likes as l where l.memberId=:memberId and l.waffleId=:waffleId",Likes.class)
                .setParameter("memberId", likesRequestDto.getMemberId())
                .setParameter("waffleId", likesRequestDto.getWaffleId())
                .getResultList().stream().findAny();
    }
}
