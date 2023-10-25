package io.github.teamwaff1e.waffle.domain.member.dao;

import io.github.teamwaff1e.waffle.domain.member.entity.Member;
import io.github.teamwaff1e.waffle.global.dao.CrudDao;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public Member updateById(Member member, String nickname) { //
        member.updateNickname(nickname);
        // entityManager.merge(member);
        return member;
    }


    @Override
    public void delete(Member member) {
        entityManager.remove(member);
    }

    public boolean existsByNickname(String nickname) {
        List<Member> members = entityManager.createQuery("select m from Member m where m.nickname = :nickname", Member.class)
                .setParameter("nickname", nickname)
                .getResultList();
        return members.stream().findAny().isPresent();
    }

    public boolean existsByEmail(String email) {
        List<Member> members = entityManager.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();
        return members.stream().findAny().isPresent();
    }
}
