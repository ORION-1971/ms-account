package com.ms_account.exception;


import lombok.Data;

@Data
public class AccountIncorrectData {            // Данные для отображения ошибки в Postman

    private int status;                        // Статус
    private String message;                    // Сообщение ошибки
    private long timestamp;                    // Время мс

}
