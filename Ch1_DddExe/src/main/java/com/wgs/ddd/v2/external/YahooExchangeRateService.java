package com.wgs.ddd.v2.external;

import com.wgs.ddd.external.Yahooforex;
import com.wgs.ddd.v2.domain.ExchangeRateDP;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class YahooExchangeRateService implements ExchangeRateService {

    @Autowired
    private Yahooforex yahooforex;

    @Override
    public ExchangeRateDP getExchangeRate(String sourceCurrency, String targetCurrency) {
        if (sourceCurrency.equals(targetCurrency)) {
            return new ExchangeRateDP(BigDecimal.ONE);
        }

        BigDecimal exchangeRate = yahooforex.getExchangeRate(sourceCurrency, targetCurrency);
        return new ExchangeRateDP(exchangeRate);
    }
}
