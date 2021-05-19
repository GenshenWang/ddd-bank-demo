package com.wgs.ddd.v2;

import com.wgs.ddd.v2.domain.AccountDP;
import com.wgs.ddd.v2.domain.ExchangeRateDP;
import com.wgs.ddd.v2.domain.Money;
import com.wgs.ddd.v2.external.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Domain Service
 */
public class AccountTransferServiceImpl implements AccountTransferService {


    @Override
    public void transfer(AccountDP sourceAccount, AccountDP targetAccount, Money transferMoney, ExchangeRateDP exchangeRate) {

        Money transferAmount = exchangeRate.exchangeTo(transferMoney);

        sourceAccount.withdraw(transferAmount);
        targetAccount.deposit(transferAmount);
    }
}
