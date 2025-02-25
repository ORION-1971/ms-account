package com.ms_account.service;

import com.ms_account.entity.Account;
import com.ms_account.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void findAll() {
        List<Account> accounts = List.of(new Account(),new Account());              // Создаем список объектов Account для возврата
        when(accountRepository.findAll()).thenReturn(accounts);                     // Настраиваем мок репозитория, чтобы он возвращал список объектов Account
        List<Account> actual = accountService.findAll();                            // Вызываем метод findAll

        verify(accountRepository, times(1)).findAll();       // Проверяем, что метод findAll был вызван
        assertEquals(accounts, actual);                                             // Проверяем, что возвращенный список соответствует ожидаемому
        assertEquals(2, actual.size());
    }

    @Test
    void findById() {
        Account account = new Account();                                             // Создаем объект Account для поиска
        when(accountRepository.findById(333L)).thenReturn(Optional.of(account));     // Настраиваем мок репозитория, чтобы он возвращал Optional с объектом Account
        Account actual = accountService.findById(333L);                              // Вызываем метод findById

        verify(accountRepository, times(1)).findById(333L);   // Проверяем, что метод findById был вызван с правильным идентификатором
        assertNotNull(actual);                                                       // Проверяем, что возвращенный объект является null
        assertEquals(account, actual);                                               // Проверяем, что возвращенный объект соответствует ожидаемому
    }

    @Test
    void save() {
        Account account = new Account();                                             // Создаем объект Account для сохранения
        when(accountRepository.save(account)).thenReturn(account);                   // Настраиваем мок репозитория, чтобы он возвращал сохраненный объект
        Account actual = accountService.save(account);                               // Вызываем метод save

        verify(accountRepository, times(1)).save(account);     // Проверяем, что метод save был вызван с правильным объектом
        assertEquals(account, actual);                                                // Проверяем, что возвращенный объект соответствует ожидаемому
    }

    @Test
    void delete() {
        Long id = 333L;
        accountService.delete(id);                                                     // Вызываем метод delete
        verify(accountRepository, times(1)).deleteById(id);     // Проверяем, что метод deleteById был вызван ровно один раз с правильным id
    }

    @Test
    void getAccountLogin() {
        List<Account> accounts = List.of(new Account(),new Account());                 // Создаем список объектов Account для возврата
        when(accountRepository.getAccount("Login")).thenReturn(accounts);        // Настраиваем мок репозитория, чтобы он возвращал список объектов Account
        List<Account> actual = accountService.getAccountLogin("Login");                // Вызываем метод getAccount

        verify(accountRepository, times(1)).getAccount("Login"); // Проверяем, что метод getAccount был вызван с правильным логином
        assertEquals(accounts, actual);                                                // Проверяем, что возвращенный список соответствует ожидаемому
        assertEquals(2, actual.size());
    }
}