package com.wgs.ddd.repository;

import com.wgs.ddd.AccountId;
import com.wgs.ddd.AccountNo;
import com.wgs.ddd.UserId;
import com.wgs.ddd.domain.entity.AccountDP;

/**
 * 账户存储模块
 */
public interface AccountRepository {
    AccountDP findByUserId(UserId userId);

    AccountDP findByAccountId(AccountId accountId);

    AccountDP findByAccountNo(AccountNo accountNo);

    AccountDP save(AccountDP accountDP);
}
