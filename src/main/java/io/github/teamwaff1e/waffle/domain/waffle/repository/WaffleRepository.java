package io.github.teamwaff1e.waffle.domain.waffle.repository;

import io.github.teamwaff1e.waffle.domain.waffle.dao.WaffleDao;
import io.github.teamwaff1e.waffle.domain.waffle.dto.request.UpdateWaffleRequestDto;
import io.github.teamwaff1e.waffle.domain.waffle.entity.Waffle;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    public Waffle likeById(Long waffleId) {
        return waffleDao.likeById(waffleId);
    }

    public Waffle unlikeById(Long waffleId) {
        return waffleDao.unlikeById(waffleId);
    }

    public Waffle find(Long waffleId) {
        return waffleDao.findById(waffleId).orElseThrow(IllegalArgumentException::new);
    }

    public List<Waffle> findAll() {
        return waffleDao.findAll();
    }

    public Waffle update(UpdateWaffleRequestDto updateWaffleRequestDto) {

        return waffleDao.updateContentById(
                updateWaffleRequestDto.getWaffleId(),
                updateWaffleRequestDto.getContent());
    }
}
