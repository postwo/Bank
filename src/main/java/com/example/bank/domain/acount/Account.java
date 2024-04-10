package com.example.bank.domain.acount;




import com.example.bank.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor // 스프링이 User 객체생성할 때 빈생성자로 new를 하기 때문
@Getter
@EntityListeners(AuditingEntityListener.class) //AuditingEntityListener는 스프링 데이터 JPA에서 제공하는 리스너 클래스 중 하나로, 주로 생성일자(createdDate)나 수정일자(modifiedDate)와 같은 감사 정보를 엔티티에 자동으로 설정하기 위해 사용됩니다
@Table(name = "account_tb")
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false,length = 20)
    private Long number; //계좌 번호
    @Column(nullable = false,length = 4)
    private Long password; // 계좌 비번
    @Column(nullable = false)
    private Long balance; // 잔액(기본값 1000원) // 남은잔액

    //항상 orm에서 fk의 주인은 many entity 쪽이다
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @CreatedDate //insert
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate // insert,update
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public Account(Long id, Long number, Long password, Long balance, User user, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.number = number;
        this.password = password;
        this.balance = balance;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
