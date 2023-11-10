package io.github.teamwaff1e.waffle.domain.waffle.dto.response;

import io.github.teamwaff1e.waffle.domain.waffle.dto.converter.WaffleToResponseConverter;
import io.github.teamwaff1e.waffle.domain.waffle.entity.Waffle;
import io.github.teamwaff1e.waffle.domain.waffle.util.ScrollPaginationCollection;
import io.github.teamwaff1e.waffle.global.dto.response.ResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetWaffleListResponseDto implements ResponseDto {

    private static final WaffleToResponseConverter converter  = new WaffleToResponseConverter();

    private static final long LAST_CURSOR = -1L;  // 다음 스크롤 없을시 lastcursor값
    
    private List<WaffleResponseDto> contents = new ArrayList<>();  // 최종적으로 클라에 넘길 값
    private long totalWaffleNumber;  // 조회 가능한 와플 수
    private long nextCursor;  // 다음 스크롤에서 사용할 커서 값

    private GetWaffleListResponseDto(List<WaffleResponseDto> contents, long totalWaffleNumber, long nextCursor) {
        this.contents = contents;
        this.totalWaffleNumber = totalWaffleNumber;
        this.nextCursor = nextCursor;
    }

    public static GetWaffleListResponseDto of(ScrollPaginationCollection<Waffle> feedsScroll, long totalWaffleNumber) {
        if (feedsScroll.isLastScroll()) {
            return GetWaffleListResponseDto.newLastScroll(feedsScroll.getCurrentScrollItems(), totalWaffleNumber);
        }
        return GetWaffleListResponseDto.newScrollHasNext(feedsScroll.getCurrentScrollItems(), totalWaffleNumber, feedsScroll.getNextCursor().getId());
    }

    private static GetWaffleListResponseDto newLastScroll(List<Waffle> feedsScroll, long totalWaffleNumber) {
        return newScrollHasNext(feedsScroll, totalWaffleNumber, LAST_CURSOR);
    }

    private static GetWaffleListResponseDto newScrollHasNext(List<Waffle> feedsScroll, long totalWaffleNumber, long nextCursor) {
        return new GetWaffleListResponseDto(getContents(feedsScroll), totalWaffleNumber, nextCursor);
    }

    private static List<WaffleResponseDto> getContents(List<Waffle> feedsScroll) {
        return feedsScroll.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
