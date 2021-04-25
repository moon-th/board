package jpa.practice.board.search;

import jpa.practice.board.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
public class BoardSearch {

    private String category; // 검색조건
    private String search;   // 검색어
    private String sort;     // 정렬기준
    private String orderBy;  // 정렬순서

    public BoardSearch(String sort, String orderBy) {
        this.sort = sort;
        this.orderBy = orderBy;
    }
}
