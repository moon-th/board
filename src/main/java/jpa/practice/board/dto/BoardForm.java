package jpa.practice.board.dto;

import jpa.practice.board.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardForm {

    private String title; //제목
    private String content; //내용
    private Member member; //작성자



}
