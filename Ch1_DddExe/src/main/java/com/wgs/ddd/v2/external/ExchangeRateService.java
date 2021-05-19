package com.wgs.ddd.v2.external;

import com.wgs.ddd.v2.domain.ExchangeRateDP;

import java.math.BigDecimal;

public interface ExchangeRateService {

    ExchangeRateDP getExchangeRate(String sourceCurrency,
                                   String targetCurrency);

}
