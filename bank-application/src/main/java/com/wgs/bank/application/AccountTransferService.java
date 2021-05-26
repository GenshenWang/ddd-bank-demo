package com.wgs.bank.application;


import java.math.BigDecimal;

public interface AccountTransferService {

    void transfer(Long userId, String targetAccountNo,
                  BigDecimal amount, String currency);
}
