package io.github.teamwaff1e.waffle.domain.likes.repository;

import io.github.teamwaff1e.waffle.domain.likes.dao.LikesDao;
import io.github.teamwaff1e.waffle.domain.likes.dto.request.LikesRequestDto;
import io.github.teamwaff1e.waffle.domain.likes.entity.Likes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class LikesRepository {

    private final LikesDao likesDao;

    public Optional<Likes> findOneByMemberAndWaffleId(Long memberId, Long waffleId) {
        return likesDao.findOneByMemberAndWaffleId(memberId, waffleId);
    }

}
