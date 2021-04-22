package jpa.practice.board.service;

import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpa.practice.board.entity.Board;
import jpa.practice.board.repository.BoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardJpaRepository boardJpaRepository;
    //전체 게시물 조회
    public Page<Board> getBoardList( int page) {
//     boardJpaRepository.findAll(); 단순 data-JPA 사용
        PageRequest pageRequest = PageRequest.of(page, 15, Sort.by(Sort.Direction.DESC, "createdDate"));
        return boardJpaRepository.findByBoard(pageRequest);
    }

}