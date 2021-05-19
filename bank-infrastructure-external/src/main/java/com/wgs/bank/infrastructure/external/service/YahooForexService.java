package com.wgs.bank.infrastructure.external.service;

import com.wgs.ddd.Currency;
import com.wgs.ddd.domain.entity.ExchangeRateDP;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class YahooForexService {

    public BigDecimal getExchangeRate(Currency sourceCurrency, Currency targetCurrency) {
        return new BigDecimal("6.8");
    }
}
