package jpa.practice.board.search;

import jpa.practice.board.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter @Setter
public class BoardSearch {

    private String category; //검색조건
    private String search; //검색어
}
