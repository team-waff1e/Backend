package io.github.teamwaff1e.waffle.domain.waffle.repository;

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

    
    public Waffle save(Waffle waffle) {
        return waffleDao.save(waffle);
    }

    public void delete(Long waffleId) {
        Waffle waffle = waffleDao.findById(waffleId).orElseThrow(IllegalArgumentException::new);
        waffleDao.delete(waffle);
    }

    public Waffle like(Long waffleId) {
        Waffle waffle = waffleDao.findById(waffleId).orElseThrow(IllegalArgumentException::new);
        waffle.like();
        return waffle;
    }

    public Waffle unlike(Long waffleId) {
        Waffle waffle = waffleDao.findById(waffleId).orElseThrow(IllegalArgumentException::new);
        waffle.unlike();
        return waffle;
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

    public Waffle increaseCommentCount(Long waffleId) {
        Waffle waffle = waffleDao.findById(waffleId).orElseThrow(IllegalArgumentException::new);
        waffle.increaseCommentCount();

        return waffle;
    }

    public Waffle decreaseCommentCount(Long waffleId) {
        Waffle waffle = waffleDao.findById(waffleId).orElseThrow(IllegalArgumentException::new);
        waffle.decreaseCommentCount();

        return waffle;
    }
}
