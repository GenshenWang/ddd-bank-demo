package com.wgs.ddd.v2;

import com.wgs.ddd.domain.Result;
import com.wgs.ddd.exception.InvalidAccountException;
import com.wgs.ddd.v2.domain.*;
import com.wgs.ddd.v2.external.ExchangeRateService;
import com.wgs.ddd.v2.messaging.MessageSender;
import com.wgs.ddd.v2.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * Application Service：只作为编排的服务
 */
public class TransferServiceV2Impl implements TransferServiceV2 {


    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ExchangeRateService exchangeRateService;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private AccountTransferService accountTransferService;

    @Override
    public Result<Boolean> transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency) {

        // 参数校验
        Money targetMoney = new Money(targetAmount, targetCurrency);

        // 获取账号
        AccountDP sourceAccount = accountRepository.find(new UserId(sourceUserId));
        AccountDP targetAccount = accountRepository.find(new AccountNo(targetAccountNumber));

        // 业务参数校验
        if (sourceAccount.isInvalid() || targetAccount.isInvalid()) {
            throw new InvalidAccountException("Invalid account.");
        }
        if (!targetAccount.getCurrency().equals(targetCurrency)) {
            throw new RuntimeException("Invalid currency.");
        }

        // 转账 (Domain Service，重点理解下，一般是跨领域服务)
        ExchangeRateDP exchangeRate = exchangeRateService.getExchangeRate(sourceAccount.getCurrency(), targetAccount.getCurrency());
        accountTransferService.transfer(sourceAccount, targetAccount, targetMoney, exchangeRate);

        // 保存数据
        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        // 发送消息
        messageSender.sender(new AuditMessage());

        return Result.success();
    }
}
