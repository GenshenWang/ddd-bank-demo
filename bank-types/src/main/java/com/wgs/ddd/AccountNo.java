package com.wgs.ddd;

import com.wgs.ddd.exception.InvalidParamException;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountNo {
    private String accountNo;

    public AccountNo(String accountNo) {
        if (accountNo == null || accountNo.length() <= 0) {
            throw new InvalidParamException();
        }

        this.accountNo = accountNo;
    }
}
