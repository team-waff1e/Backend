package io.github.teamwaff1e.waffle.domain.waffle.util;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ScrollPaginationCollection<T> {

    private final List<T> itemsWithNextCursor; // 현재 스크롤의 요소 + 다음 스크롤의 요소 1개 (다음 스크롤 있는지 확인)
    private final int limit;

    public static <T> ScrollPaginationCollection<T> of(List<T> itemsWithNextCursor, int limit) {
        return new ScrollPaginationCollection<>(itemsWithNextCursor, limit);
    }

    public boolean isLastScroll() {
        return this.itemsWithNextCursor.size() <= limit;
    }

    public List<T> getCurrentScrollItems() {
        if (isLastScroll()) {
            return this.itemsWithNextCursor;
        }
        return this.itemsWithNextCursor.subList(0, limit);
    }

    public T getNextCursor() {
        return itemsWithNextCursor.get(limit - 1);
    }

}