package io.github.teamwaff1e.waffle.domain.member.repository;

import io.github.teamwaff1e.waffle.domain.member.dao.MemberDao;
import io.github.teamwaff1e.waffle.domain.member.dto.request.UpdateMemberRequestDto;
import io.github.teamwaff1e.waffle.domain.member.entity.Follow;
import io.github.teamwaff1e.waffle.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public Member update(long id, UpdateMemberRequestDto updateMemberRequestDto) {
        Member member = memberDao.findById(id).orElseThrow(IllegalArgumentException::new);
        return memberDao.updateById(member,
                updateMemberRequestDto.getNickname()
        );
    }

    public void delete(Long id) {
        Member member = memberDao.findById(id).orElseThrow(IllegalArgumentException::new);
        memberDao.delete(member);
    }

    public void follow(Follow follow){
        memberDao.follow(follow);
    }
    public List<Follow> ReadFollow(Long memberId){

        return memberDao.findFollowById(memberId);
    }
}
