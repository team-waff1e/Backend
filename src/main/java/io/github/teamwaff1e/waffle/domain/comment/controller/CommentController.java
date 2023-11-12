package io.github.teamwaff1e.waffle.domain.comment.controller;

import io.github.teamwaff1e.waffle.domain.auth.vo.AuthVo;
import io.github.teamwaff1e.waffle.domain.comment.dto.request.CreateCommentRequestDto;
import io.github.teamwaff1e.waffle.domain.comment.dto.request.UpdateCommentRequestDto;
import io.github.teamwaff1e.waffle.domain.comment.dto.response.CommentResponseDto;
import io.github.teamwaff1e.waffle.domain.comment.service.CommentService;
import io.github.teamwaff1e.waffle.global.annotation.Login;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public void createComment(@Login AuthVo authVo, @PathVariable @NotNull @Positive Long waffleId,
                              @Validated @RequestBody CreateCommentRequestDto commentRequestDto) {

        // if authVo is null?
        commentService.createComment(authVo, waffleId, commentRequestDto);
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
    public CommentResponseDto updateComment(@Login AuthVo authVo,
                                            @PathVariable @NotNull @Positive Long waffleId,
                                            @PathVariable @NotNull @Positive Long commentId,
                                            @Validated @RequestBody UpdateCommentRequestDto commentRequestDto) {

        return commentService.updateComment(authVo, waffleId, commentId, commentRequestDto);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@Login AuthVo authVo,
                              @PathVariable @NotNull @Positive Long waffleId,
                              @PathVariable @NotNull @Positive Long commentId) {

        commentService.deleteComment(authVo, waffleId, commentId);
    }
}
