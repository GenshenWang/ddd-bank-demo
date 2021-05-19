package com.wgs.ddd;

import com.wgs.bank.application.AccountTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class BankTransferController {

    @Autowired
    private AccountTransferService accountTransferService;

    @GetMapping(value = "/bank/transfer")
    public Result transfer(@RequestParam("sourceUser") Long userId,
                           @RequestParam("sourceAccount") String sourceAccountNo,
                           @RequestParam("targetAccount") String targetAccountNo,
                           @RequestParam("amount") BigDecimal amount,
                           @RequestParam("currency") String currency) {

        accountTransferService.transfer(userId, targetAccountNo, amount, currency);
        return Result.success();
    }

    @GetMapping(value = "/bank/transfer/list")
    public Result<BankTransferDTO> list(@RequestParam("sourceUser") Long userId,
                                        @RequestParam("accountNo") String accountNo) {

        return new Result().success(new BankTransferDTO());
    }
}
