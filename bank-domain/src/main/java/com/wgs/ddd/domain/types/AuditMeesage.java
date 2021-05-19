package com.wgs.ddd.domain.types;

import com.wgs.ddd.Currency;
import com.wgs.ddd.Money;
import com.wgs.ddd.UserId;
import com.wgs.ddd.domain.entity.AccountDP;
import lombok.Data;

@Data
public class AuditMeesage {
    private UserId sourceUser;
    private AccountDP sourceAccount;
    private AccountDP targetAccount;
    private Money transferMoney;
    private Currency currency;

    public String serialize() {
        return String.format("User[%s] transfer money:[%s] from accountNo:[%s] to accountNo:[%s]",
                sourceUser.getId(), transferMoney.getValue(), sourceAccount.getAccountId(), targetAccount.getAccountId());
    }

    // TODO: 2021-05-17
    public AuditMeesage desrialize() {
        return null;
    }

}
