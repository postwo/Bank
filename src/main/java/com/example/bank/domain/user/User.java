package com.example.bank.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor // 스프링이 User 객체생성할 때 빈생성자로 new를 하기 때문
@Getter
@EntityListeners(AuditingEntityListener.class) //AuditingEntityListener는 스프링 데이터 JPA에서 제공하는 리스너 클래스 중 하나로, 주로 생성일자(createdDate)나 수정일자(modifiedDate)와 같은 감사 정보를 엔티티에 자동으로 설정하기 위해 사용됩니다
@Table(name = "user_tb")
@Entity
public class User { // extends 시간설정 (상속)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false,length = 20)
    private String username;

    @Column(nullable = false,length = 60)
    private String password;

    @Column(nullable = false,length = 20)
    private String email;

    @Column(nullable = false,length = 20)
    private String fullname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserEnum role; //ADMIN,CUSTOMER
    @CreatedDate //insert
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate // insert,update
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public User(Long id, String username, String password, String email, String fullname, UserEnum role, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
