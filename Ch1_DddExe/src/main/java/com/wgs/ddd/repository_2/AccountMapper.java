package com.wgs.ddd.repository_2;

import com.wgs.ddd.domain.AccountDO;

public interface AccountMapper {
    AccountDO selectByUserId(Long userId);

    AccountDO selectByAccountId(Long accountId);

    AccountDO selectByAccountNo(String accountNo);

    void update(AccountDO accountDO);

    void insert(AccountDO accountDO);

}
