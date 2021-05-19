package com.wgs.ddd.controller;

import com.wgs.ddd.domain.Result;
import com.wgs.ddd.service.TransferService;

import java.math.BigDecimal;

public class TransferController {

    private TransferService transferService;

    /**
     * 用户userId所属账户 往targetAccountNumber 账户转账amount
     *
     * @param targetAccountNumber
     * @param amount
     * @param userId
     * @return
     */
    public Result<Boolean> transfer(String targetAccountNumber, BigDecimal amount, Long userId) {
        return transferService.transfer(userId, targetAccountNumber, amount, "CNY");
    }
}
