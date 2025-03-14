package com.ms_account.dto;

import org.jasypt.util.text.AES256TextEncryptor;

public class Password {

    String secret = "account";

    public String encryptPassword(String password) {                      //Шифратор
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(secret);

        String encrypted = encryptor.encrypt(password);
        System.out.println(encrypted);
        return encrypted;
    }

    public String decryptPassword(String encrypted) {                    //Дешифратор
        AES256TextEncryptor decryptor = new AES256TextEncryptor();
        decryptor.setPassword(secret);

        String decrypted = decryptor.decrypt(encrypted);
        System.out.println(decrypted);
        return decrypted;
    }

}
