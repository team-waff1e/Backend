package io.github.teamwaff1e.waffle.domain.Member.repository;

import io.github.teamwaff1e.waffle.domain.Member.dao.MemberDao;
import io.github.teamwaff1e.waffle.domain.Member.dto.request.UpdateMemberRequestDto;
import io.github.teamwaff1e.waffle.domain.Member.entity.Member;
import io.github.teamwaff1e.waffle.domain.comment.dto.request.UpdateCommentRequestDto;
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
