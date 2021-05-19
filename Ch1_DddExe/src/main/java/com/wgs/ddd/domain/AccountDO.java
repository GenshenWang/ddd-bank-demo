package com.wgs.ddd.domain;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDO {

    private String accountNo;
    private Integer status;
    private BigDecimal availableMoney;
    private BigDecimal dailyLimit;
    /**
     * 费率
     */
    private String currency;

}
