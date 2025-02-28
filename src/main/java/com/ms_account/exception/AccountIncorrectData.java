package com.ms_account.exception;


import lombok.Data;

@Data
public class AccountIncorrectData {

    private int status;
    private String message;
    private long timestamp;

}
