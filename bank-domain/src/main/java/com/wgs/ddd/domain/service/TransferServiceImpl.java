package com.wgs.ddd.domain.service;

import com.wgs.ddd.Money;
import com.wgs.ddd.domain.entity.AccountDP;
import com.wgs.ddd.domain.entity.ExchangeRateDP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class TransferServiceImpl implements TransferService {


    @Override
    public void transferMoney(AccountDP sourceAccount, AccountDP targetAccount, Money targetAmount, ExchangeRateDP exchangeRateDP) {
        // 中国账户，想转600美元到美国账户。
        // 此处targetAmount即为600美元，所以需要计算出原账户扣除的金额
        Money sourceAmount = exchangeRateDP.exchangeTo(targetAmount);
        // 事务保证
        sourceAccount.withdraw(sourceAmount);
        targetAccount.deposit(targetAmount);
    }
}
