package com.ms_account.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RestControllerAdvice
public class AccountExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AccountIncorrectData handleAccountException(MethodArgumentNotValidException ex, HttpServletRequest request) {

        var processKey = UUID.randomUUID().toString();
        log.error("Service error, status: uuid: {}, message {}", processKey, ex.getMessage());

        return AccountIncorrectData.builder()                  //404 "Неверные данные";
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())        //2 вариант - .status(HttpStatus.BAD_REQUEST)
                .message(ex.getFieldError().getDefaultMessage())
                .path(request.getRequestURI())
                .code("1")
               // .uuid(processKey)
                .build();
    }

}
