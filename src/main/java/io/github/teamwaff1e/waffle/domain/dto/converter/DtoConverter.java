package io.github.teamwaff1e.waffle.domain.dto.converter;

@FunctionalInterface
public interface DtoConverter<T, R> {

    R convert(T obj);
}
