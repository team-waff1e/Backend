package io.github.teamwaff1e.waffle.domain.comment.controller;

import io.github.teamwaff1e.waffle.domain.comment.dto.request.CreateCommentRequestDto;
import io.github.teamwaff1e.waffle.domain.comment.dto.request.UpdateCommentRequestDto;
import io.github.teamwaff1e.waffle.domain.comment.dto.response.CommentResponseDto;
import io.github.teamwaff1e.waffle.domain.comment.service.CommentService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/waffles/{waffleId}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto createComment(@Validated @ModelAttribute CreateCommentRequestDto commentRequestDto,
                                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return null;
        }

        return commentService.createComment(commentRequestDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponseDto> readCommentList(@PathVariable @NotNull @Positive Long waffleId) {
        return null;
    }

    @GetMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentResponseDto readComment(@PathVariable @NotNull @Positive Long waffleId,
                                          @PathVariable @NotNull @Positive Long commentId) {
        return commentService.readComment(waffleId, commentId);
    }

    @PatchMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentResponseDto updateComment(@Validated @ModelAttribute UpdateCommentRequestDto commentRequestDto,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return null;
        }

        return commentService.updateComment(commentRequestDto);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable @NotNull @Positive Long waffleId,
                              @PathVariable @NotNull @Positive Long commentId) {
        commentService.deleteComment(waffleId, commentId);
    }
}
