package com.wgs.ddd.v2;

import com.wgs.ddd.v2.domain.AccountDP;
import com.wgs.ddd.v2.domain.ExchangeRateDP;
import com.wgs.ddd.v2.domain.Money;


/**
 * Domain Service
 */
public interface AccountTransferService {

    /**
     * 转账操作
     *
     * @param sourceAccount
     * @param targetAccount
     * @param transferMoney
     */
    void transfer(AccountDP sourceAccount, AccountDP targetAccount,
                  Money transferMoney, ExchangeRateDP exchangeRate);
}
