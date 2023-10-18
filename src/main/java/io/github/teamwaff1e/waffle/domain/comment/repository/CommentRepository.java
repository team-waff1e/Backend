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
    public Comment find(Long commentId) {
        return commentDao.findById(commentId).orElseThrow(() -> new IllegalArgumentException());
    }

    public Comment update(UpdateCommentRequestDto commentRequestDto) {
        return commentDao.updateById(
                commentRequestDto.getCommentId(),
                commentRequestDto.getContent()
        );
    }

    public void delete(Long id) {
        commentDao.deleteById(id);
    }
}
