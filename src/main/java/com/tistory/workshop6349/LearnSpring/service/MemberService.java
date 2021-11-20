package com.tistory.workshop6349.LearnSpring.service;

import com.tistory.workshop6349.LearnSpring.domain.Member;
import com.tistory.workshop6349.LearnSpring.repository.MemberRepository;
import com.tistory.workshop6349.LearnSpring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * @param member 멤버
     * @return member Id
    */

    public Long join(Member member) {
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 전체 회원 조회
     * @return 전체 회원 리스트를 반환합니다.
    * */

    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    /**
     * 아이디로 검색
     * @param memberId 멤버 Id
     * @return memberId 로 찾은 멤버 반환
    * */

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


    private void validateDuplicateMember(Member member) {
        // 이미 존재한 이름으로는 join 불가
        // 같은 이름이 있는 중복 회원 X
        memberRepository.findByName(member.getName())
                .ifPresent(alreadyMember -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

}
