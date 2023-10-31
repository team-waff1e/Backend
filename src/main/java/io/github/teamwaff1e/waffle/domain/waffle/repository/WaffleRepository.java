package io.github.teamwaff1e.waffle.domain.waffle.repository;

import io.github.teamwaff1e.waffle.domain.likes.dao.LikesDao;
import io.github.teamwaff1e.waffle.domain.likes.dto.request.LikesRequestDto;
import io.github.teamwaff1e.waffle.domain.member.dao.MemberDao;
import io.github.teamwaff1e.waffle.domain.waffle.dao.WaffleDao;
import io.github.teamwaff1e.waffle.domain.waffle.dto.request.UpdateWaffleRequestDto;
import io.github.teamwaff1e.waffle.domain.waffle.entity.Waffle;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class WaffleRepository {

    private final WaffleDao waffleDao;
    //    private final MemberDao memberDao;  // TODO member waffle 분리
    private final LikesDao likesDao;
    
    public Waffle save(Waffle waffle) {
        return waffleDao.save(waffle);
    }

    public void delete(Long waffleId) {
        Waffle waffle = waffleDao.findById(waffleId).orElseThrow(IllegalArgumentException::new);
        waffleDao.delete(waffle);
    }

    public Waffle like(LikesRequestDto likesRequestDto) {
        Waffle waffle = waffleDao.findById(likesRequestDto.getWaffleId()).orElseThrow(IllegalArgumentException::new);
        waffle.like();

        likesDao.like(likesRequestDto);
        return waffle;
    }

    public Waffle unlike(LikesRequestDto likesRequestDto) {
        Waffle waffle = waffleDao.findById(likesRequestDto.getWaffleId()).orElseThrow(IllegalArgumentException::new);
        waffle.unlike();

        likesDao.unlike(likesRequestDto);
        return waffle;
    }

    public boolean isLiked(LikesRequestDto likesRequestDto) {
        return likesDao.findLikesById(likesRequestDto) != null;
    }

    public Waffle update(UpdateWaffleRequestDto updateWaffleRequestDto) {
        Waffle waffle = waffleDao.findById(updateWaffleRequestDto.getWaffleId()).orElseThrow(IllegalArgumentException::new);
        waffle.updateWaffleContent(updateWaffleRequestDto.getContent());
        return waffle;
    }

    public Waffle find(Long waffleId) {
        return waffleDao.findById(waffleId).orElseThrow(IllegalArgumentException::new);
    }

    public List<Waffle> findAll() {
        return waffleDao.findAll();
    }
}
