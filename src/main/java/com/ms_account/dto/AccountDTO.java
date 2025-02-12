package com.ms_account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {                            // РАБОТАЕТ НА УРОВНЕ КОНТРОЛЛЕРА

    private String login;

    private String password;

}
