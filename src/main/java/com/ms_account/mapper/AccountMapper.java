package com.ms_account.mapper;


import com.ms_account.dto.AccountDTO;
import com.ms_account.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

   // AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);


    Account convertToAccount(AccountDTO accountDTO);                         // конвертер Account -> AccountDTO

    //void convertToAccountA(AccountDTO accountDTO, Account account);           // перенос данных User > UserDTO

    AccountDTO convertToAccountDTO(Account account);                         // конвертер AccountDTO -> Account

    List<AccountDTO> listToAccountDTO(List<Account> accountList);            // конвертер СПИСКА AccountDTO -> Account
}
