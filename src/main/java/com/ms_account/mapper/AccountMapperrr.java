package com.ms_account.mapper;


import com.ms_account.dto.AccountDTO;
import com.ms_account.entity.Account;

//@Mapper
public interface AccountMapperrr {

   // AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account toAccount (AccountDTO accountDTO);
    AccountDTO toAccountDTO (Account account);
}
