package com.wgs.ddd;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BankTransferDTO {
    private Long userId;
    private String accountNo;
    private BigDecimal amount;
    private String currency;
    private Date date;
}
