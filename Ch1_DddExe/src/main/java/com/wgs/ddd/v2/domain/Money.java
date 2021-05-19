package com.wgs.ddd.v2.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class Money {

    private BigDecimal money;
    /**
     * 费率类型，如CNY
     */
    private String currency;

    public Money(BigDecimal money, String currency) {
        if (money == null || BigDecimal.ZERO.compareTo(money) < 0) {
            throw new RuntimeException("Invalid money.");
        }
        if (currency == null) {
            throw new RuntimeException("Invalid currency.");
        }
        this.currency = currency;
    }

    public Money(BigDecimal money) {
        this.money = money;
    }

    public Money add(Money addMoney) {
        return new Money(this.money.add(addMoney.getMoney()));
    }

    public Money devide(BigDecimal param) {
        return new Money(this.money.divide(param, RoundingMode.DOWN));
    }

    public Money subtract(Money subtractMoney) {
        return new Money(this.money.subtract(subtractMoney.getMoney()));
    }

    public int compareTo(Money money) {
        return this.money.compareTo(money.getMoney());
    }
}
