package com.wgs.ddd.service;

import com.wgs.ddd.domain.Result;

import java.math.BigDecimal;

/**
 * 转账服务
 */
public interface TransferService {

    Result<Boolean> transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency);

}
