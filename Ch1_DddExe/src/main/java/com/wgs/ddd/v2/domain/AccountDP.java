package com.wgs.ddd.v2.domain;

import com.wgs.ddd.exception.DailyLimitException;
import com.wgs.ddd.exception.InsufficienttException;
import com.wgs.ddd.exception.InvalidCurrencytException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccountDP {
    private AccountId id;
    private UserId userId;
    private AccountNo accountNo;
    private Integer status;
    private Money available;
    private Money dailyLimit;

    public String getCurrency() {
        return this.available.getCurrency();
    }

    /**
     * 转入
     *
     * @param money
     */
    public void deposit(Money money) {
        if (!this.available.getCurrency().equals(money.getCurrency())) {
            throw new InvalidCurrencytException();
        }
        this.available = this.available.add(money);
    }


    /**
     * 转出
     *
     * @param money
     */
    public void withdraw(Money money) {
        if (this.available.compareTo(money) < 0) {
            throw new InsufficienttException();
        }
        if (this.dailyLimit.compareTo(money) < 0) {
            throw new DailyLimitException();
        }
        this.available = this.available.subtract(money);
    }

    public boolean isInvalid() {
        return this.status == 1;
    }


}
