package jpa.practice.board.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpa.practice.board.entity.Board;
import jpa.practice.board.entity.QBoard;
import jpa.practice.board.entity.QMember;
import jpa.practice.board.search.BoardSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static jpa.practice.board.entity.QBoard.*;
import static jpa.practice.board.entity.QMember.member;
import static org.springframework.util.StringUtils.hasText;
import static org.springframework.util.StringUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class BoardQuerydslRepository {

    private final EntityManager em;

    private final JPAQueryFactory query;

    /**
     * 메인보드 검색조건으로 검색
     *
     * @param page
     * @param boardSearch
     */
    public Page<Board> getSearchBoardList(Pageable page, BoardSearch boardSearch) {

        QueryResults<Board> boardQueryResults = query
                .select(board)
                .from(board)
                .join(board.member, member)
                .where(
                        searchEq(boardSearch)
                ).offset(page.getOffset())
                .limit(page.getPageSize())
                .orderBy(boardSort(page))
                .fetchResults();
        List<Board> content = boardQueryResults.getResults();
        long total = boardQueryResults.getTotal();
        return new PageImpl<>(content, page, total);
    }

    /**
     * 리스트 정렬
     * @param page
     * @return
     */
    private OrderSpecifier<?> boardSort(Pageable page) {

        if (!page.getSort().isEmpty()) {
            for (Sort.Order order : page.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()){
                    case "title":
                        return new OrderSpecifier(direction, board.title);
                    case "content":
                        return new OrderSpecifier(direction, board.content);
                    case "member":
                        return new OrderSpecifier(direction, board.member.memberName);
                    case "createdDate":
                        return new OrderSpecifier(direction, board.createdDate);
                }
            }
        }
        return null;
    }

    /**
     * 조건문
     * @param boardSearch
     * @return
     */
    private BooleanExpression searchEq(BoardSearch boardSearch) {
        BooleanExpression expression = null;
        if (hasText(boardSearch.getCategory()) && hasText(boardSearch.getSearch())) {
            if (boardSearch.getCategory().equals("title")) {
                expression = board.title.like("%"+boardSearch.getSearch()+"%");
            } else if (boardSearch.getCategory().equals("content")) {
                expression = board.content.like("%"+boardSearch.getSearch()+"%");
            } else if (boardSearch.getCategory().equals("member")) {
                expression = board.member.memberName.like("%"+boardSearch.getSearch()+"%");
            }
        }
        return expression;
    }
}