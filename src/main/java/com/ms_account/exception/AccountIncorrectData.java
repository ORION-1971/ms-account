package com.ms_account.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
//@NoArgsConstructor
@Builder
public class AccountIncorrectData {                  // Данные для отображения ошибки в Postman

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp;

    private final Integer status;                    // Статус 2 вариант -  HttpStatus status;
    private final String message;                    // Сообщение ошибки
    private final String path;                       // место ошибки

    private final String code;                       // еще один код
    private final String uuid;                       // uuid код ошибки

}
