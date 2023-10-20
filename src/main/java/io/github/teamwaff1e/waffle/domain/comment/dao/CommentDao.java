package io.github.teamwaff1e.waffle.domain.comment.dao;

import io.github.teamwaff1e.waffle.domain.comment.entity.Comment;
import io.github.teamwaff1e.waffle.global.dao.CrudDao;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class CommentDao implements CrudDao<Comment, Long> {

    private final EntityManager entityManager;

    @Override
    public Comment save(Comment comment) {
        entityManager.persist(comment);
        return comment;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findById(Long id) { // todo: throw exception?
        Comment comment = entityManager.find(Comment.class, id);
        return Optional.ofNullable(comment);
    }

    public Comment update(Comment comment, String content) { // todo: 변경에 취약해지는 문제 발생
        comment.updateComment(content);
        return comment;
    }

    @Override
    public void delete(Comment comment) {
        entityManager.remove(comment);
    }
}
