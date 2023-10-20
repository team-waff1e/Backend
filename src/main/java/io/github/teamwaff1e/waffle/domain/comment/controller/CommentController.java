package io.github.teamwaff1e.waffle.domain.comment.controller;

import io.github.teamwaff1e.waffle.domain.comment.dto.request.CreateCommentRequestDto;
import io.github.teamwaff1e.waffle.domain.comment.dto.request.UpdateCommentRequestDto;
import io.github.teamwaff1e.waffle.domain.comment.dto.response.CommentResponseDto;
import io.github.teamwaff1e.waffle.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/waffles/{waffleId}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto createComment(@ModelAttribute CreateCommentRequestDto commentRequestDto, BindingResult bindingResult) {
        return commentService.createComment(commentRequestDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponseDto> readCommentList(@PathVariable Long waffleId) {
        return null;
    }

    @GetMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentResponseDto readComment(@PathVariable Long waffleId, @PathVariable Long commentId) {
        return commentService.readComment(commentId);
    }

    @PatchMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentResponseDto updateComment(@PathVariable Long waffleId, @ModelAttribute UpdateCommentRequestDto commentRequestDto,
                                 BindingResult bindingResult) {
        return commentService.updateComment(commentRequestDto);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long waffleId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
