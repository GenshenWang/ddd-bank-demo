package com.wgs.ddd.v2.repository;

import com.wgs.ddd.v2.domain.AccountDP;
import com.wgs.ddd.v2.domain.AccountId;
import com.wgs.ddd.v2.domain.AccountNo;
import com.wgs.ddd.v2.domain.UserId;

public interface AccountRepository {

    AccountDP find(AccountId accountId);

    AccountDP find(AccountNo accountNo);

    AccountDP find(UserId userId);

    AccountDP save(AccountDP account);
}
