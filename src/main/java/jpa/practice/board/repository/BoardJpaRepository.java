package jpa.practice.board.repository;

import jpa.practice.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardJpaRepository extends JpaRepository<Board,Long> {

    @Query(value = "select b from Board b join b.member m",
            countQuery = "select count(b) from Board b")
    Page<Board> findByBoard(Pageable pageable);

}
