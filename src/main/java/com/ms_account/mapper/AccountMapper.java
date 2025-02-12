package com.ms_account.mapper;

import com.ms_account.dto.AccountDTO;
import com.ms_account.entity.Account;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class AccountMapper {
    ModelMapper modelMapper = new ModelMapper();

    public Account convertToAccount(AccountDTO accountDTO) {                            // конвертер Account -> AccountDTO
        return modelMapper.map(accountDTO, Account.class);
    }

    public void convertToAccount(AccountDTO accountDTO, Account account) {                 // перенос данных User > UserDTO
        modelMapper.map(accountDTO, account);
    }

    public AccountDTO convertToAccountDTO(Account account) {                             // конвертер AccountDTO -> Account
        return modelMapper.map(account, AccountDTO.class);
    }
                                                                                      // конвертер СПИСКА AccountDTO -> Account
    public List<AccountDTO> listToAccountDTO(List<Account> accountList) {
        return accountList.stream()
                .map(account -> modelMapper
                        .map(account, AccountDTO.class))
                .collect(Collectors.toList());
    }
}
