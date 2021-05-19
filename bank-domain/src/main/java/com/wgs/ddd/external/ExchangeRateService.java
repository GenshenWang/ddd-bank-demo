package com.wgs.ddd.external;

import com.wgs.ddd.Currency;
import com.wgs.ddd.domain.entity.ExchangeRateDP;

public interface ExchangeRateService {


    ExchangeRateDP getExchangeRate(Currency sourceCurrency, Currency targetCurrency);


}
