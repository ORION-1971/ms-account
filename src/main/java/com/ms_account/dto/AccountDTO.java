package com.ms_account.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {                             // РАБОТАЕТ НА УРОВНЕ КОНТРОЛЛЕРА

    @NotNull(message = "userId не прошла проверку")   // Отображение сообщения в консоли
    private Long userId;

    @NotNull(message = "cardId не прошла проверку")
    private Long cardId;

    @NotBlank(message = "Логин не может быть пустым")
    private String login;

    @NotBlank(message = "Пароль не может быть пустым")
    private String password;

}
