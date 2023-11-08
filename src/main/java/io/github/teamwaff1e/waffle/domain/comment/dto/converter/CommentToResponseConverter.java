package io.github.teamwaff1e.waffle.domain.comment.dto.converter;

import io.github.teamwaff1e.waffle.domain.comment.dto.response.CommentResponseDto;
import io.github.teamwaff1e.waffle.domain.comment.entity.Comment;
import io.github.teamwaff1e.waffle.global.dto.converter.DtoConverter;

public class CommentToResponseConverter implements DtoConverter<Comment, CommentResponseDto> {

    @Override
    public CommentResponseDto convert(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .waffleId((comment.getWaffle().getId()))
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }
}
