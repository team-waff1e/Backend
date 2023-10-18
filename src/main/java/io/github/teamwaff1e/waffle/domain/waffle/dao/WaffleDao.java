package io.github.teamwaff1e.waffle.domain.waffle.dao;

import io.github.teamwaff1e.waffle.domain.dao.CrudDao;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Transactional
public class WaffleDao implements CrudDao {
    private final EntityManager entityManager;

    public WaffleDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
