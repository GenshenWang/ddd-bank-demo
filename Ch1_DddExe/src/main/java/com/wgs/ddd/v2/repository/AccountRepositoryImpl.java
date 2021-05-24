package com.wgs.ddd.v2.repository;

import com.wgs.ddd.domain.AccountDO;
import com.wgs.ddd.repository_2.AccountMapper;
import com.wgs.ddd.v2.domain.AccountDP;
import com.wgs.ddd.v2.domain.AccountId;
import com.wgs.ddd.v2.domain.AccountNo;
import com.wgs.ddd.v2.domain.UserId;

public class AccountRepositoryImpl implements AccountRepository {

    private AccountMapper accountMapper;

    @Override
    public AccountDP find(AccountId accountId) {
        AccountDO accountDO = accountMapper.selectByUserId(accountId.getId());
        return AccountBuilder.toAccount(accountDO);
    }

    @Override
    public AccountDP find(AccountNo accountNo) {
        AccountDO accountDO = accountMapper.selectByAccountNo(accountNo.getAccountNo());
        return AccountBuilder.toAccount(accountDO);
    }

    @Override
    public AccountDP find(UserId userId) {
        AccountDO accountDO = accountMapper.selectByUserId(userId.getId());
        return AccountBuilder.toAccount(accountDO);
    }

    @Override
    public AccountDP save(AccountDP account) {
        AccountDO accountDO = AccountBuilder.fromAccount(account);
        if (account.getId() == null) {
            accountMapper.insert(accountDO);
        } else {
            accountMapper.update(accountDO);
        }

        // 把id返回
        return AccountBuilder.toAccount(accountDO);
    }
}
