package com.example.bank.domain.transaction;

import com.example.bank.domain.acount.Account;
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
@Table(name = "user_tb")
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Account withdrawAccount;
    private Account depositAccount;

    private Long amount; //얼마나 입금했는지

    private Long withdrawAccountBalance; //1111 계좌 -> 1000원  이체된 내역을 확인하기 위해 사용
    private Long depositAccountBalance; // 입급 내역

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) // db에서 인식할수 있게
    private TransactionEnum gubun; // WITHDRAW,DEPOSIT, TRANSFER,ALL

    //계좌가 사라져도 로그는 남아야 한다
    private String sender;
    private String receiver;
    private String tel;

    @CreatedDate //insert
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate // insert,update
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public Transaction(Long id, Account withdrawAccount, Account depositAccount, Long amount, Long withdrawAccountBalance, Long depositAccountBalance, TransactionEnum gubun, String sender, String receiver, String tel, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.withdrawAccount = withdrawAccount;
        this.depositAccount = depositAccount;
        this.amount = amount;
        this.withdrawAccountBalance = withdrawAccountBalance;
        this.depositAccountBalance = depositAccountBalance;
        this.gubun = gubun;
        this.sender = sender;
        this.receiver = receiver;
        this.tel = tel;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
