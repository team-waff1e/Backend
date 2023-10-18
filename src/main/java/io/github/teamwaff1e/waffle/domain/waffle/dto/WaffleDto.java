package io.github.teamwaff1e.waffle.domain.waffle.dto;

import io.github.teamwaff1e.waffle.domain.waffle.entity.Waffle;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class WaffleDto {

    private Long id;
    private String content;
    private String createdAt;
    private String updatedAt;
    private Integer likes;
    private Long memberId;

    public Waffle toEntity() {
        Waffle waffle = Waffle.builder()
                .id(id)
                .content(content)
                .createdAt(Timestamp.valueOf(createdAt))
                .updatedAt(Timestamp.valueOf(updatedAt))
                .likes(likes)
                .memberId(memberId)
                .build();
        return waffle;
    }

    @Builder
    public WaffleDto(Long id, String content, String createdAt, String updatedAt, Integer likes, Long memberId) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.likes = likes;
        this.memberId = memberId;
    }
}
