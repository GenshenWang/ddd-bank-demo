package com.wgs.ddd.v2;

import com.wgs.ddd.domain.Result;

import java.math.BigDecimal;

public interface TransferServiceV2 {

    Result<Boolean> transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency);

}
