package jpa.practice.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "board")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board extends BasicEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore @Column(name = "board_id")
    private Long boardId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //작성자

    private String title; //제목

    private String content; //내용

    @Column(name = "LIKE_COUNT")
    private Long likeCount; //좋아요

    public Board(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
    }
}
