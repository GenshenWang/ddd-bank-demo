package com.wgs.ddd;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class Currency {
    private String currency;
    private BigDecimal rate;

    public Currency(String currency) {
        this.currency = currency;
    }

    public Currency(String currency, BigDecimal rate) {
        this.currency = currency;
        this.rate = rate;
    }
}
