package jpa.practice.board.service;

import jpa.practice.board.entity.Member;
import jpa.practice.board.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;

    @Transactional(readOnly = true)
    public int dpCheck(String MemberName){
       return memberJpaRepository.findByMemberName(MemberName);
    }

}
