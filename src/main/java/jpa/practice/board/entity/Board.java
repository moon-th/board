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

    @Id @GeneratedValue
    @JsonIgnore @Column(name = "board_id")
    private Long boardId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //작성자

    private String title; //제목

    private String content; //내용


}
