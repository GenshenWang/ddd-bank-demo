package com.wgs.ddd.v2.domain;

import lombok.Data;

@Data
public class AccountNo {
    private String accountNo;

    public AccountNo(String accountNo) {
        if (accountNo == null || accountNo.length() <= 0) {
            throw new RuntimeException("Invalid accountNo:" + accountNo);
        }
        this.accountNo = accountNo;
    }
}
