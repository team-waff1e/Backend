package io.github.teamwaff1e.waffle.domain.auth.repository;

import io.github.teamwaff1e.waffle.domain.member.dao.MemberDao;
import io.github.teamwaff1e.waffle.domain.member.entity.Member;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class AuthRepository {

    private final MemberDao memberDao;

    public Member save(Member member) {
        return memberDao.save(member);
    }

    public boolean existsByNickname(String nickname) {
        return memberDao.existsByNickname(nickname);
    }

    public boolean existsByEmail(String email) {
        return memberDao.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findValidMember(String email, String pwd) {
        List<Member> members = memberDao.findAllByEmail(email);

        return members.stream()
                .filter(member -> member.getPwd().equals(pwd))
                .findAny();
    }
}
