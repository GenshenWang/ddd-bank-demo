package com.wgs.ddd.v2.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class ExchangeRateDP {
    private BigDecimal exchangeRate;

    public ExchangeRateDP(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Money exchangeTo(Money amount) {
        return amount.devide(this.exchangeRate);
    }
}
