package io.github.teamwaff1e.waffle.domain.waffle.dao;

import io.github.teamwaff1e.waffle.global.dao.CrudDao;
import io.github.teamwaff1e.waffle.domain.waffle.entity.Waffle;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Transactional
@Component
@RequiredArgsConstructor
public class WaffleDao implements CrudDao<Waffle, Long> {

    private final EntityManager entityManager;

    @Override
    public Waffle save(Waffle waffle) {
        entityManager.persist(waffle);
        return waffle;
    }

    @Override
    public Optional<Waffle> findById(Long waffleId) {
        Waffle waffle = entityManager.find(Waffle.class, waffleId);
        return Optional.ofNullable(waffle);
    }

    @Override
    public void delete(Waffle waffle) {
        entityManager.remove(waffle);
    }

    public List<Waffle> findAll() {
        return null;
    }
}
