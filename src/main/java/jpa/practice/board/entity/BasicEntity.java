package jpa.practice.board.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class) //audit 을 이용하면 자동으로 시간을 매핑하여 데이터베이스의 테이블에 넣어주게 됩니다.
public class BasicEntity {

    @CreatedDate
    @Column(updatable = false,name = "DT_REG")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "UDT_REG")
    private LocalDateTime lastModifiedDate;

}
