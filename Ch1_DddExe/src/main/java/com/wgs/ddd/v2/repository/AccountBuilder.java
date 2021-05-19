package com.wgs.ddd.v2.repository;

import com.wgs.ddd.domain.AccountDO;
import com.wgs.ddd.v2.domain.AccountDP;

public class AccountBuilder {
    public static AccountDP toAccount(AccountDO accountDo) {
        return new AccountDP();
    }


    public static AccountDO fromAccount(AccountDP accountDP) {
        return new AccountDO();
    }

}
