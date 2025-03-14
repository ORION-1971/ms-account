package com.ms_account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms_account.dto.AccountDTO;
import com.ms_account.entity.Account;
import com.ms_account.mapper.AccountMapper;
import com.ms_account.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountControllerTest {

    @Mock
    private AccountService service;

    @Mock
    private AccountMapper mapper;


    @InjectMocks
    private AccountController accountController;

    private MockMvc mockMvc; // Объект для выполнения запросов к контроллеру

    Account account1 = new Account(1L, 1L, 1L, "testLogin1", "testPassword1", null, null);
    Account account2 = new Account(2L, 2L, 2L, "testLogin2", "testPassword2", null, null);
    List<Account> accounts = Arrays.asList(account1, account2);

    // Подготовка DTO
    AccountDTO accountDTO1 = new AccountDTO(1L, 1L, "testLogin1", "testPassword1");
    AccountDTO accountDTO2 = new AccountDTO(2L, 2L, "testLogin2", "testPassword2");
    List<AccountDTO> accountDTOs = Arrays.asList(accountDTO1, accountDTO2);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Инициализация моков
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    void getAccount() throws Exception {

        // Настройка поведения мока
        when(service.findAll()).thenReturn(accounts);
        when(mapper.listToAccountDTO(accounts)).thenReturn(accountDTOs);

        // Выполнение запроса и проверка результата
        mockMvc.perform(get("/account")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(1)) // Проверка userId
                .andExpect(jsonPath("$[0].cardId").value(1)) // Проверка cardId
                .andExpect(jsonPath("$[0].login").value("testLogin1"))
                .andExpect(jsonPath("$[0].password").value("testPassword1"));
        mockMvc.perform(get("/account")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].userId").value(2)) // Проверка userId
                .andExpect(jsonPath("$[1].cardId").value(2)) // Проверка cardId
                .andExpect(jsonPath("$[1].login").value("testLogin2"))
                .andExpect(jsonPath("$[1].password").value("testPassword2"));

        verify(service, times(2)).findAll();
        verify(mapper, times(2)).listToAccountDTO(accounts);

    }

    @Test
    void findById() throws Exception {
        when(service.findById(1L)).thenReturn(account1);
        when(mapper.convertToAccountDTO(account1)).thenReturn(accountDTO1);

        mockMvc.perform(get("/account/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1)) // Проверка userId
                .andExpect(jsonPath("$.cardId").value(1)) // Проверка cardId
                .andExpect(jsonPath("$.login").value("testLogin1"))
                .andExpect(jsonPath("$.password").value("testPassword1"));

        verify(service, times(1)).findById(1L);
        verify(mapper, times(1)).convertToAccountDTO(account1);
    }

    @Test
    void createAccount() throws Exception {
        when(mapper.convertToAccount(accountDTO1)).thenReturn(account1);
        when(service.save(account1)).thenReturn(account1);

        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":1,\"cardId\":1,\"login\":\"testLogin1\",\"password\":\"testPassword1\"}"))
                .andExpect(status().isOk());

        verify(mapper, times(1)).convertToAccount(accountDTO1);
        verify(service, times(1)).save(account1);
    }

    @Test
    void updateAccount() throws Exception {
        // Подготовка данных
        ObjectMapper objectMapper = new ObjectMapper();
        AccountDTO accountDTO = new AccountDTO(1L, 1L, "testLoginUpdated", "testPasswordUpdated");

        String accountJson = objectMapper.writeValueAsString(accountDTO);

        when(service.findById(1L)).thenReturn(account1);
        when(service.save(account1)).thenReturn(account1);

        // Выполнение запроса на обновление аккаунта
        mockMvc.perform(put("/account/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(accountJson))
                .andExpect(status().isOk());

        // Проверка взаимодействий
        verify(service, times(1)).findById(1L);
        verify(mapper, times(1)).convertToAccount(accountDTO, account1);
        verify(service, times(1)).save(account1);
    }

    @Test
    void deleteAccount() throws Exception {
        // Выполнение запроса на удаление аккаунта
        mockMvc.perform(delete("/account/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Проверка взаимодействия с сервисом
        verify(service, times(1)).delete(1L);
    }

    @Test
    void getAccountLogin() throws Exception {
        // Подготовка данных
        List<Account> accounts = Collections.singletonList(account1);
        when(service.getAccountLogin("testLogin1")).thenReturn(accounts);

        // Выполнение запроса для получения аккаунта по логину
        mockMvc.perform(get("/account/login/testLogin1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].login").value("testLogin1"))
                .andExpect(jsonPath("$[0].password").value("testPassword1"));

        // Проверка взаимодействия с сервисом
        verify(service, times(1)).getAccountLogin("testLogin1");
    }
}

