package com.wgs.ddd.builder;

import com.wgs.ddd.domain.entity.AccountId;
import com.wgs.ddd.domain.entity.Currency;
import com.wgs.ddd.domain.entity.AccountNo;
import com.wgs.ddd.domain.entity.UserId;
import com.wgs.ddd.domain.entity.Money;

import com.wgs.ddd.domain.AccountDO;
import com.wgs.ddd.domain.entity.AccountDP;
import org.springframework.stereotype.Component;

@Component
public class AccountBuilder {

    public AccountDP toAccount(AccountDO accountDO) {
        AccountDP accountDP = new AccountDP();
        accountDP.setUserId(new UserId(accountDO.getUserId()));
        accountDP.setAccountId(new AccountId(accountDO.getAccountId()));
        accountDP.setAccountNo(new AccountNo(accountDO.getAccountNo()));
        accountDP.setCurrency(new Currency(accountDO.getCurrency()));
        accountDP.setAvailable(new Money(accountDO.getAvailable()));
        accountDP.setDailyLimit(new Money(accountDO.getDailyLimit()));
        accountDP.setStatus(accountDO.getStatus());

        return accountDP;
    }

    public AccountDO fromAccount(AccountDP accountDP) {
        AccountDO accountDO = new AccountDO();
        accountDO.setId(accountDP.getAccountId().getId());
        accountDO.setUserId(accountDP.getUserId().getId());
        accountDO.setAccountId(accountDP.getAccountId().getId());
        accountDO.setAccountNo(accountDP.getAccountNo().getAccountNo());
        accountDO.setCurrency(accountDP.getCurrency().getCurrency());
        accountDO.setAvailable(accountDP.getAvailable().getValue());
        accountDO.setDailyLimit(accountDP.getDailyLimit().getValue());
        accountDO.setStatus(accountDO.getStatus());

        return accountDO;
    }
}
