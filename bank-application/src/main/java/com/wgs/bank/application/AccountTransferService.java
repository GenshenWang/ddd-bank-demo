package com.wgs.bank.application;

import com.wgs.ddd.domain.entity.Currency;
import com.wgs.ddd.domain.entity.Money;

import java.math.BigDecimal;

public interface AccountTransferService {

    void transfer(Long userId, String targetAccountNo,
                  BigDecimal amount, String currency);
}
