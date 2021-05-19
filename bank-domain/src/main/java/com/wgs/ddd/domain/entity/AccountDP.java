package com.wgs.ddd.domain.entity;

import com.wgs.ddd.*;
import com.wgs.ddd.exception.DailyLimitException;
import com.wgs.ddd.exception.InsufficientException;
import lombok.*;


/**
 * 账户领域模型
 */
@Data
public class AccountDP {
    private UserId userId;
    private AccountId accountId;
    private AccountNo accountNo;
    private Currency currency;
    private Money available;
    private Money dailyLimit;
    private Integer status;

    /**
     * 存入
     */
    public void deposit(Money amount) {
        this.available.add(amount);
    }

    /**
     * 提取
     *
     * @param amount
     */
    public void withdraw(Money amount) {
        if (this.dailyLimit.compareTo(amount) < 0) {
            throw new DailyLimitException("Withdraw money:" + amount.getValue() + " exceed account daily limit with:" + dailyLimit.getValue());
        }
        if (this.available.compareTo(amount) < 0) {
            throw new InsufficientException("Withdraw money:" + amount.getValue() + " excedd account avaiable money with:" + available.getValue());
        }
        this.available.subtract(amount);
    }

    public boolean isValid() {
        return status == 1;
    }

}
