package com.ms_account.repository;

import com.ms_account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @NativeQuery("select * from accounts where login = :login")              // Выбрать всех Account, где логин равен URL /Gunay
    List<Account> getAccount(@Param("login") String login);                  // Указание параметра "login" как возраст

    // ---------------------------------------------------------

    /*@Query("select a from Account a where u.login = :login")               // Выбрать всех Account, где возраст меньше заданного в URL /30
    List<Account> getAccount(@Param("login") String login);                 // Указание параметра "age" как возраст*/

    //----------------------------------------------------------

    //List<Account> getAccountByLoginEquals(String login);                   // Параметризованный запрос SQL

}
