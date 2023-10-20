package io.github.teamwaff1e.waffle.global.dao;

import java.util.Optional;

public interface CrudDao<T, ID> {

    T save(T entity);

    Optional<T> findById(ID id);

    void delete(T entity);
}