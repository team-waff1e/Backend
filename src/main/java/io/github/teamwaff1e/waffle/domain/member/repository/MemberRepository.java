package io.github.teamwaff1e.waffle.domain.member.repository;

import io.github.teamwaff1e.waffle.domain.member.dao.MemberDao;
import io.github.teamwaff1e.waffle.domain.member.dto.request.UpdateMemberRequestDto;
import io.github.teamwaff1e.waffle.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class MemberRepository {

    private final MemberDao memberDao;

    public Member save(Member member) {
        return memberDao.save(member);
    }

    public Member find(Long memberId) {
        return memberDao.findById(memberId).orElseThrow(() -> new IllegalArgumentException());
    }

    public Member update(long memberId, UpdateMemberRequestDto updateMemberRequestDto) {
        return memberDao.updateById(memberId,
                updateMemberRequestDto.getNickname()
        );
    }

    public void delete(Long id) {
        memberDao.deleteById(id);
    }
}
