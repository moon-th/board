package jpa.practice.board.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpa.practice.board.entity.Board;
import jpa.practice.board.entity.QBoard;
import jpa.practice.board.entity.QMember;
import jpa.practice.board.search.BoardSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static jpa.practice.board.entity.QBoard.*;
import static jpa.practice.board.entity.QMember.member;
import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
public class BoardQuerydslRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    /**
     * 메인보드 검색조건으로 검색
     * @param page
     * @param boardSearch
     */
    public Page<Board> getSearchBoardList(int page, BoardSearch boardSearch) {

        QueryResults<Board> boardQueryResults = query
                .select(board)
                .from(board)
                .join(board.member, member)
                .where(
                        searchEq(boardSearch)
                ).offset(page)
                .limit(15)
                .fetchResults();
        List<Board> content = boardQueryResults.getResults();
        long total = boardQueryResults.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression searchEq(BoardSearch boardSearch) {
        BooleanExpression expression = null;
        if(hasText(boardSearch.getCategory())){
            if(boardSearch.getCategory().equals("title")){
                expression = board.title.eq(boardSearch.getSearch());
            }else if(boardSearch.getCategory().equals("content")){
                expression = board.content.eq(boardSearch.getSearch());
            }else if(boardSearch.getCategory().equals("member")){
                expression = board.member.memberName.eq(boardSearch.getSearch());
            }
        }
        return expression;
}
