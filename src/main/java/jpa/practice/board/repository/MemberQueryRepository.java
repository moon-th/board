package jpa.practice.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jpa.practice.board.entity.Member;
import jpa.practice.board.entity.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static jpa.practice.board.entity.QMember.member;


@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final EntityManager em;

    private final JPAQueryFactory query;

    public Member findLoginMember(Member loginMember) {
        return query
                .select(QMember.member)
                .from(QMember.member)
                .where(
                        QMember.member.memberName.eq(loginMember.getMemberName())
                                .and(QMember.member.password.eq(loginMember.getPassword())))
                .fetchOne();
    }
}
