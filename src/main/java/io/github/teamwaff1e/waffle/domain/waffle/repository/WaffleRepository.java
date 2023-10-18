package io.github.teamwaff1e.waffle.domain.waffle.repository;

import io.github.teamwaff1e.waffle.domain.waffle.dao.WaffleDao;
import io.github.teamwaff1e.waffle.domain.waffle.dto.WaffleDto;
import io.github.teamwaff1e.waffle.domain.waffle.entity.Waffle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WaffleRepository {

    private final WaffleDao waffleDao;
//    private final MemberDao memberDao;

    public WaffleRepository(WaffleDao waffleDao) {
        this.waffleDao = waffleDao;
//        this.memberDao = memberDao;
    }
    
    public Waffle save(Waffle waffle) {
        return waffleDao.save(waffle);
    }

    public String deleteById(String waffleId) {
        return waffleDao.deleteById(waffleId);
    }

    public String likeById(String waffleId) {
    }

    public String unlikeById(String waffleId) {
    }

    public Optional<Waffle> findById(String waffleId) {
        return waffleDao.findById(waffleId);
    }

    public List<Waffle> findAll() {
        return waffleDao.findAll();  // LAZY
    }

    public WaffleDto update(Waffle waffle) {
        return waffleDao.updateById(waffleId, waffle);
    }
}
