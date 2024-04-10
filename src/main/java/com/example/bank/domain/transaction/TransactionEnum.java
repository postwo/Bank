package com.example.bank.domain.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionEnum {
    WITHDRAW("출근"),DEPOSIT("입금"),TRANSFER("이체"),ALL("입출금내역");

    private String value; //값을 받을수 있는 변수
}
