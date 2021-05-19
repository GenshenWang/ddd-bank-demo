package com.wgs.ddd.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDO {
    private Long id;
    private Long userId;
    private Long AccountId;
    private String accountNo;
    private String currency;
    private Integer status;

    private BigDecimal available;
    private BigDecimal dailyLimit;
}
