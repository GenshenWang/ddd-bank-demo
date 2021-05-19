package com.wgs.ddd.domain.entity;

import com.wgs.ddd.Currency;
import com.wgs.ddd.Money;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class ExchangeRateDP {

    private BigDecimal rate;
    private Currency source;
    private Currency target;

    public ExchangeRateDP(BigDecimal rate, Currency source, Currency target) {
        this.rate = rate;
        this.source = source;
        this.target = target;
    }

    public Money exchangeTo(Money amount) {

        BigDecimal targetMoney = amount.getValue().divide(rate, RoundingMode.DOWN);
        return new Money(targetMoney);
    }
}
