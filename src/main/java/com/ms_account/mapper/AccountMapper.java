package com.ms_account.mapper;


import com.ms_account.dto.AccountDTO;
import com.ms_account.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    Account convertToAccount(AccountDTO accountDTO);                         // конвертер Account -> AccountDTO

    AccountDTO convertToAccountDTO(Account account);                         // конвертер AccountDTO -> Account

    List<AccountDTO> listToAccountDTO(List<Account> accountList);            // конвертер СПИСКА AccountDTO -> Account

    default void convertToAccount(AccountDTO accountDTO, Account account) {  // перенос данных User > UserDTO
        account.setUserId(accountDTO.getUserId());
        account.setCardId(accountDTO.getCardId());
        account.setLogin(accountDTO.getLogin());
        account.setPassword(accountDTO.getPassword());
    }
}
