package com.wgs.ddd.repository_2;

import com.wgs.ddd.AccountId;
import com.wgs.ddd.AccountNo;
import com.wgs.ddd.UserId;
import com.wgs.ddd.builder.AccountBuilder;
import com.wgs.ddd.domain.AccountDO;
import com.wgs.ddd.domain.entity.AccountDP;
import com.wgs.ddd.mapper.AccountMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AccountRepositoryImpl implements AccountRepository {

    @Resource
    private AccountMapper accountMapper;
    @Resource
    private AccountBuilder accountBuilder;

    @Override
    public AccountDP findByUserId(UserId userId) {
        AccountDO accountDO = accountMapper.selectByUserId(userId.getId());
        return accountBuilder.toAccount(accountDO);
    }

    @Override
    public AccountDP findByAccountId(AccountId accountId) {
        AccountDO accountDO = accountMapper.selectById(accountId.getId());
        return accountBuilder.toAccount(accountDO);
    }

    @Override
    public AccountDP findByAccountNo(AccountNo accountNo) {
        AccountDO accountDO = accountMapper.selectByAccountNo(accountNo.getAccountNo());
        return accountBuilder.toAccount(accountDO);
    }

    @Override
    public AccountDP save(AccountDP accountDP) {
        AccountDO accountDO = accountBuilder.fromAccount(accountDP);
        if (accountDP.getAccountId() == null) {
            accountMapper.insert(accountDO);
        } else {
            accountMapper.update(accountDO);
        }

        return accountBuilder.toAccount(accountDO);
    }
}
