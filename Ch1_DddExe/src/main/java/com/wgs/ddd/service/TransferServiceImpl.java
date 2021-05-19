package com.wgs.ddd.service;

import com.wgs.ddd.domain.AccountDO;
import com.wgs.ddd.domain.Result;
import com.wgs.ddd.exception.InvalidAccountException;
import com.wgs.ddd.external.KafkaSender;
import com.wgs.ddd.external.Yahooforex;
import com.wgs.ddd.repository.AccountMapper;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TransferServiceImpl implements TransferService {

    private AccountMapper accountMapper;
    private Yahooforex yahooforex;
    private KafkaSender kafkaSender;

    /**
     * 可维护性：中间件的升级、外部服务的变化都会导致此处核心代码需要改动
     * 可扩展性：新增跨行转账时，此处代码无法复用。大量依赖现有的AccountDO数据结构，无法复用。
     * 如果以后有别的Account类型，就不适用
     * 可测试性：依赖大量的外部服务、中间件、存储层，搭建环境复杂，不容易测试
     *
     * @param sourceUserId
     * @param targetAccountNo
     * @param targetAmount
     * @param targetCurrency
     * @return
     */
    @Override
    public Result<Boolean> transfer(Long sourceUserId, String targetAccountNo,
                                    BigDecimal targetAmount, String targetCurrency) {
        // 1. 获取账号
        AccountDO sourceAccount = accountMapper.selectByUserId(sourceUserId);
        AccountDO targetAccount = accountMapper.selectByAccountNo(targetAccountNo);
        if (sourceAccount == null || targetAccount == null) {
            throw new InvalidAccountException("Invalid account");
        }

        // 2. 业务参数校验
        if (!targetAccount.getCurrency().equals(targetCurrency)) {
            throw new RuntimeException("Invalid currency.");
        }

        // 3. 调用外部服务，转换费率
        BigDecimal exchangeRate = BigDecimal.ONE;
        if (!sourceAccount.getCurrency().equals(targetAccount.getCurrency())) {
            exchangeRate = yahooforex.getExchangeRate(sourceAccount.getCurrency(), targetAccount.getCurrency());
        }
        BigDecimal sourceAmount = targetAmount.divide(exchangeRate, RoundingMode.DOWN);

        // 4. 业务参数校验
        if (sourceAccount.getAvailableMoney().compareTo(sourceAmount) < 0) {
            throw new RuntimeException("Insufficient available money.");
        }
        if (sourceAccount.getDailyLimit().compareTo(sourceAmount) < 0) {
            throw new RuntimeException("Exceed account daily limit.");
        }

        // 5. 更新余额
        BigDecimal sourceAvailable = sourceAmount.subtract(sourceAmount);
        sourceAccount.setAvailableMoney(sourceAvailable);
        BigDecimal targetAvailable = sourceAmount.add(sourceAmount);
        targetAccount.setAvailableMoney(targetAvailable);

        // 6. 调用存储层，更新DB
        accountMapper.update(sourceAccount);
        accountMapper.update(targetAccount);

        // 7. 调用中间件，发送Kafka消息
        String msg = sourceUserId + " transfer money:" + targetAmount + " to targetNo:" + targetAccountNo;
        kafkaSender.send(msg);

        return Result.success();
    }
}
