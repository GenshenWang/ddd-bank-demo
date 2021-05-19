package com.wgs.bank.application;

import com.wgs.bank.application.exception.InvalidAccountException;
import com.wgs.ddd.AccountNo;
import com.wgs.ddd.Money;
import com.wgs.ddd.domain.service.TransferService;
import com.wgs.ddd.UserId;
import com.wgs.ddd.domain.entity.*;
import com.wgs.ddd.messaging.MessageProducer;
import com.wgs.ddd.repository.AccountRepository;
import com.wgs.ddd.external.ExchangeRateService;
import com.wgs.ddd.domain.types.AuditMeesage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class AccountTransferServiceImpl implements AccountTransferService {

    public static final String TRANSFER_ACCOUNT_KEY = "TransferAccount";
    @Resource
    private AccountRepository accountRepository;
    @Resource
    private ExchangeRateService exchangeRateService;
    @Resource
    private MessageProducer messageProducer;
    @Resource
    private TransferService transferService;

    @Override
    public void transfer(Long userId, String targetAccountNo, BigDecimal amount, String currency) {
        // 查询账户
        AccountDP sourceAccount = accountRepository.findByUserId(new UserId(userId));
        AccountDP targetAccount = accountRepository.findByAccountNo(new AccountNo(targetAccountNo));
        if (!sourceAccount.isValid() || !targetAccount.isValid()) {
            throw new InvalidAccountException();
        }

        // 获取、计算费率
        ExchangeRateDP exchangeRate = exchangeRateService.getExchangeRate(sourceAccount.getCurrency(), targetAccount.getCurrency());
        Money targetAmount = exchangeRate.exchangeTo(new Money(amount));

        // 转账操作
        transferService.transferMoney(sourceAccount, targetAccount, targetAmount);

        // 保存数据 (为什么不放到 transferService.transferMoney 里？不方便测试的原因？)
        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        // 发送消息
        AuditMeesage auditMeesage = new AuditMeesage();
        messageProducer.send(TRANSFER_ACCOUNT_KEY, auditMeesage.serialize());

    }
}
