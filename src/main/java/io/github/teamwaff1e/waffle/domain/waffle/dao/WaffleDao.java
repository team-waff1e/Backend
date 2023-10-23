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

    public Waffle updateContentById(Long waffleId, String content) {
        Waffle waffle = findById(waffleId).orElseThrow(IllegalArgumentException::new);
        waffle.updateWaffleContent(content);
        return waffle;
    }

    // TODO like logic 요구사항 도출

    public Waffle likeById(Long waffleId) {
        Waffle waffle = findById(waffleId).orElseThrow(IllegalArgumentException::new);
        waffle.like();
        return waffle;
    }
    public Waffle unlikeById(Long waffleId) {
        Waffle waffle = findById(waffleId).orElseThrow(IllegalArgumentException::new);
        waffle.unlike();
        return waffle;
    }

//    @Override
//    public void deleteById(Long waffleId) {
//        Waffle waffle = findById(waffleId).orElseThrow(() -> new IllegalArgumentException());
//        entityManager.remove(waffle);
//    }

    @Override
    public void delete(Waffle waffle) {
        entityManager.remove(waffle);
    }

    public List<Waffle> findAll() {
        return null;
    }
}
