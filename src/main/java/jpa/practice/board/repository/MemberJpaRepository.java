package jpa.practice.board.repository;


import jpa.practice.board.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Lazy;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    @Query("select count(m) from Member m where m.memberName =:memberName")
    int findByMemberName(@Param("memberName") String memberName);

}
