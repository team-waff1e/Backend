package io.github.teamwaff1e.waffle.global.dto.converter;

@FunctionalInterface
public interface DtoConverter<T, R> {

    R convert(T obj);
}
