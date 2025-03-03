package com.ms_account.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AccountExceptionHandler {

    ///----------------------------------- ПЕРВЫЙ СПОСОБ --------------------------------------
    @ExceptionHandler
    public ResponseEntity<AccountIncorrectData> handleAccountException(NotFoundException exceptiion) {
        AccountIncorrectData data = new AccountIncorrectData();

        log.error(exceptiion.getMessage(), exceptiion);

        data.setStatus(HttpStatus.NOT_FOUND);
        data.setMessage(exceptiion.getMessage());                      // "Аккаунта с id - " + id + " не существует!"

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);       // 404
    }

    ///----------------------------------- ВТОРОЙ СПОСОБ --------------------------------------
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AccountIncorrectData handleAccountException(Exception exceptiion) {

        log.error(exceptiion.getMessage(), exceptiion);

        return new AccountIncorrectData(HttpStatus.BAD_REQUEST, exceptiion.getMessage());       //400 "Неверные данные, введи Integer.");
    }
}
