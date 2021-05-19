package com.wgs.bank.infrastructure.external.service;

import com.wgs.ddd.Currency;
import com.wgs.ddd.domain.entity.ExchangeRateDP;
import com.wgs.ddd.external.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
    @Autowired
    private YahooForexService yahooForexService;

    @Override
    public ExchangeRateDP getExchangeRate(Currency sourceCurrency, Currency targetCurrency) {
        BigDecimal rate = BigDecimal.ONE;
        if (!sourceCurrency.equals(targetCurrency)) {
            rate = yahooForexService.getExchangeRate(sourceCurrency, targetCurrency);
        }

        return new ExchangeRateDP(rate, sourceCurrency, targetCurrency);
    }
}
