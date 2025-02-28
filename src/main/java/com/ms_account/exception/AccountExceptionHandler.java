package com.ms_account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccountExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<AccountIncorrectData> handleAccountException(NotFoundException exceptiion) {
        AccountIncorrectData data = new AccountIncorrectData();

        data.setStatus(HttpStatus.NOT_FOUND.value());
        data.setMessage(exceptiion.getMessage());
        data.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);       // 404
    }

    @ExceptionHandler
    public ResponseEntity<AccountIncorrectData> handleAccountException(Exception exceptiion) {
        AccountIncorrectData data = new AccountIncorrectData();

        data.setStatus(HttpStatus.BAD_REQUEST.value());
        data.setMessage(exceptiion.getMessage());              //"Неверные данные, введи цифровые значение.");
        data.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);     //400
    }
}
