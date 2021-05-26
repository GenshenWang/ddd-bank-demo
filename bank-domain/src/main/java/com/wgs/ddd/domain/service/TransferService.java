package com.wgs.ddd.domain.service;

import com.wgs.ddd.Money;
import com.wgs.ddd.domain.entity.AccountDP;
import com.wgs.ddd.domain.entity.ExchangeRateDP;

public interface TransferService {

    /**
     * 转账操作
     *
     * @param sourceAccount
     * @param targetAccount
     */
    void transferMoney(AccountDP sourceAccount, AccountDP targetAccount, Money sourceAmount, ExchangeRateDP exchangeRateDP);
}
