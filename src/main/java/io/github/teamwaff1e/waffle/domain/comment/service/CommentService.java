package io.github.teamwaff1e.waffle.domain.comment.service;

import io.github.teamwaff1e.waffle.domain.comment.dto.request.CreateCommentRequestDto;
import io.github.teamwaff1e.waffle.domain.comment.dto.request.UpdateCommentRequestDto;
import io.github.teamwaff1e.waffle.domain.comment.dto.response.CommentResponseDto;
import io.github.teamwaff1e.waffle.domain.comment.entity.Comment;
import io.github.teamwaff1e.waffle.domain.comment.repository.CommentRepository;
import io.github.teamwaff1e.waffle.domain.dto.converter.DtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final DtoConverter<Comment, CommentResponseDto> converter;

    public CommentResponseDto createComment(CreateCommentRequestDto commentRequestDto) {
        Comment newComment = Comment.builder()
                .content(commentRequestDto.getContent())
                .waffleId(commentRequestDto.getWaffleId())
                .memberId(1L) // todo: memberId
                .build();

        Comment comment = commentRepository.save(newComment);

        return converter.convert(comment);
    }

    public CommentResponseDto readComment(Long commentId) {
        Comment comment = commentRepository.find(commentId);
        return converter.convert(comment);
    }

    public CommentResponseDto updateComment(UpdateCommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.update(commentRequestDto);
        return converter.convert(comment);
    }

    public void deleteComment(Long commentId) {
        commentRepository.delete(commentId);
    }
}
