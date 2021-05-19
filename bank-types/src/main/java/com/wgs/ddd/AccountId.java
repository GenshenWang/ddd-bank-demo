package com.wgs.ddd;

import com.wgs.ddd.exception.InvalidParamException;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class AccountId {
    private Long id;

    public AccountId(Long accountId) {
        if (accountId == null || accountId <= 0) {
            throw new InvalidParamException();
        }

        id = accountId;
    }
}
