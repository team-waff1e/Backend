package io.github.teamwaff1e.waffle.domain.Member.dao;

import io.github.teamwaff1e.waffle.domain.Member.entity.Member;
import io.github.teamwaff1e.waffle.domain.dao.CrudDao;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class MemberDao implements CrudDao<Member, Long> {

    private final EntityManager entityManager;

    @Override
    public Member save(Member member) {
        entityManager.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) { // todo: throw exception?
        Member member = entityManager.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public Member updateById(Long id, String nickname) { //
        Member member = findById(id).orElseThrow(() -> new IllegalArgumentException());
        member.updateNickname(nickname);
        entityManager.merge(member);
        return member;
    }

    @Override
    public void deleteById(Long id) {
        // todo: IllegalArgumentException -> IllegalIdArgumentException
        Member member = findById(id).orElseThrow(() -> new IllegalArgumentException());
        entityManager.remove(member);
    }
}
