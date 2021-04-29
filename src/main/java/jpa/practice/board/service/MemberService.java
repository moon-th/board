package jpa.practice.board.service;

import jpa.practice.board.entity.Member;
import jpa.practice.board.repository.MemberJpaRepository;
import jpa.practice.board.repository.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;

    private final MemberQueryRepository memberQueryRepository;

    @Transactional(readOnly = true)
    public int dpCheck(String memberName){
       return memberJpaRepository.findByMemberName(memberName);
    }

    public Long joinMember(Member member){
        memberJpaRepository.save(member);
        return member.getMemberId();
    }

    public Member LoginMember(Member member){
        return memberQueryRepository.findLoginMember(member);
    }
}
