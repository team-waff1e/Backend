package io.github.teamwaff1e.waffle.domain.comment.repository;

import io.github.teamwaff1e.waffle.domain.comment.dao.CommentDao;
import io.github.teamwaff1e.waffle.domain.comment.dto.request.UpdateCommentRequestDto;
import io.github.teamwaff1e.waffle.domain.comment.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class CommentRepository {

    private final CommentDao commentDao;

    public Comment save(Comment comment) {
        return commentDao.save(comment);
    }

    @Transactional(readOnly = true)
    public Comment find(Long waffleId, Long commentId) {
        // todo: IllegalArgumentException -> IllegalIdArgumentException
        // todo: 중복 코드
        return commentDao.findById(commentId).orElseThrow(IllegalArgumentException::new);
    }

    public Comment update(UpdateCommentRequestDto commentRequestDto) {
        Long commentId = commentRequestDto.getCommentId();
        Comment comment = commentDao.findById(commentId).orElseThrow(IllegalArgumentException::new);
        String content = commentRequestDto.getContent();

        return commentDao.update(comment, content);
    }

    public void delete(Long waffleId, Long commentId) {
        Comment comment = commentDao.findById(commentId).orElseThrow(IllegalArgumentException::new);
        commentDao.delete(comment);
    }
}
