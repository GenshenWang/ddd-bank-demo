package com.wgs.ddd.repository_2;

import com.wgs.ddd.v2.domain.AccountDP;
import com.wgs.ddd.v2.domain.AccountId;

public class AccountRepository implements Repository<AccountDP, AccountId> {
    @Override
    public void attach(AccountDP aggregate) {

    }

    @Override
    public void detach(AccountDP aggregate) {

    }

    @Override
    public AccountDP find(AccountId accountId) {
        return null;
    }

    @Override
    public void remove(AccountDP aggregate) {

    }

    @Override
    public void save(AccountDP aggregate) {

    }
}
