package io.github.teamwaff1e.waffle.domain.dao;

import java.util.Optional;

public interface CrudDao<T, ID> {

    T save(T entity);

    Optional<T> findById(ID id);

    T updateById(ID id, Object updateDto);

    void deleteById(ID id);
}

// upudate dto -> service에서 까서 쓸수 x