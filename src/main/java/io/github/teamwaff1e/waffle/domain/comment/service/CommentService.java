package io.github.teamwaff1e.waffle.domain.comment.service;

import io.github.teamwaff1e.waffle.domain.auth.vo.AuthVo;
import io.github.teamwaff1e.waffle.domain.comment.dto.request.CreateCommentRequestDto;
import io.github.teamwaff1e.waffle.domain.comment.dto.request.UpdateCommentRequestDto;
import io.github.teamwaff1e.waffle.domain.comment.dto.response.CommentResponseDto;
import io.github.teamwaff1e.waffle.domain.comment.entity.Comment;
import io.github.teamwaff1e.waffle.domain.comment.repository.CommentRepository;
import io.github.teamwaff1e.waffle.domain.member.entity.Member;
import io.github.teamwaff1e.waffle.domain.member.repository.MemberRepository;
import io.github.teamwaff1e.waffle.domain.waffle.entity.Waffle;
import io.github.teamwaff1e.waffle.domain.waffle.repository.WaffleRepository;
import io.github.teamwaff1e.waffle.global.dto.converter.DtoConverter;
import io.github.teamwaff1e.waffle.global.exception.auth.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final WaffleRepository waffleRepository;
    private final MemberRepository memberRepository;

    private final DtoConverter<Comment, CommentResponseDto> converter;

    public CommentResponseDto createComment(AuthVo authVo, Long waffleId, CreateCommentRequestDto commentRequestDto) {
        String content = commentRequestDto.getContent();

        Waffle waffle = waffleRepository.find(waffleId);
        Member member = memberRepository.find(authVo.getMemberId());

        Comment newComment = Comment.builder()
                .content(content)
                .waffle(waffle)
                .member(member)
                .build();

        Comment comment = commentRepository.save(newComment);
        waffleRepository.increaseCommentCount(waffleId);

        return converter.convert(comment);
    }

    @Transactional(readOnly = true)
    public CommentResponseDto readComment(Long waffleId, Long commentId) {
        Comment comment = commentRepository.find(waffleId, commentId);
        return converter.convert(comment);
    }

    public CommentResponseDto updateComment(AuthVo authVo, Long waffleId, Long commentId,
                                            UpdateCommentRequestDto commentRequestDto) {
        // todo: waffleId, commentId validation

        Comment comment = commentRepository.find(waffleId, commentId);
        Member commentMember = comment.getMember();
        Member loginMember = memberRepository.find(authVo.getMemberId());

        if (loginMember != commentMember) {
            throw new UnauthorizedException(); // todo: error message
        }

        Comment updatedComment = commentRepository.update(waffleId, commentId, commentRequestDto);
        return converter.convert(updatedComment);
    }

    public void deleteComment(AuthVo authVo, Long waffleId, Long commentId) {

        Comment comment = commentRepository.find(waffleId, commentId);
        Member commentMember = comment.getMember();
        Member loginMember = memberRepository.find(authVo.getMemberId());

        if (loginMember != commentMember) {
            throw new UnauthorizedException(); // todo: error message
        }

        commentRepository.delete(waffleId, commentId);
        waffleRepository.decreaseCommentCount(waffleId);
    }
}
