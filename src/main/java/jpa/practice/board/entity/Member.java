package jpa.practice.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "member")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BasicEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "member_id")
    private Long memberId;

    @JsonIgnore
    private String password;

    private String memberName;

    @OneToMany(mappedBy = "member",fetch = LAZY)
    private List<Board> Boards = new ArrayList<>();


}
