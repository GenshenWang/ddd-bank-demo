package com.wgs.ddd.v2.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuditMessage {
    private UserId userId;
    private AccountNo source;
    private AccountNo target;
    private Money transferMoney;
    private Date date;

    public String serialize() {
        return "User:" + userId.toString() + "transfer " +
                transferMoney.getMoney() + " from account: "
                + source.getAccountNo() + " to account " + target.getAccountNo();
    }

    public AuditMessage deserialize(String value) {
        // TODO: 2021-05-14 deserialize
        return null;
    }


}
