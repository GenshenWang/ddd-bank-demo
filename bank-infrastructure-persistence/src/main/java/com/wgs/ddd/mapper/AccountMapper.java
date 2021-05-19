package com.wgs.ddd.mapper;

import com.wgs.ddd.domain.AccountDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

    AccountDO selectById(Long id);

    AccountDO selectByUserId(Long userId);

    AccountDO selectByAccountNo(String accountNo);

    void update(AccountDO accountDO);

    void insert(AccountDO accountDO);
}
