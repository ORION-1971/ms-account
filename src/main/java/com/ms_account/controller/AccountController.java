package com.ms_account.controller;

import com.ms_account.dto.AccountDTO;
import com.ms_account.entity.Account;
import com.ms_account.mapper.AccountMapper;
import com.ms_account.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService service;
    private final AccountMapper mapper;

    public AccountController(AccountService service, AccountMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    // *** ВЫВОД СПИСКА ***
    @GetMapping()                                                                   // Запрос GET
    public List<AccountDTO> getAccount() {
        return mapper.listToAccountDTO(service.findAll());
    }

    // *** ПОИСК по ID ***
    @GetMapping("/{id}")                                                            // Запрос GET, в URL /4
    public AccountDTO findById(@PathVariable Long id) {
        return mapper.convertToAccountDTO(service.findById(id));
    }

    // *** ДОБАВЛЕНИЕ ***
    @PostMapping()                                                                  // Запрос POST, JSON {"login": "Gunay", "password": Gunay}
    public Account createAccount(@RequestBody AccountDTO accountDTO) {
        return service.save(mapper.convertToAccount(accountDTO));
    }

    // *** ИЗМЕНЕНИЕ ***
    @PutMapping("/{id}")                                                            // Запрос PUT, в URL /4, JSON {"login": "Gunay", "password": Gunay}
    public Account updateAccount(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
        Account account = service.findById(id);
        mapper.convertToAccount(accountDTO, account);
        return service.save(account);
    }

    // *** УДАЛЕНИЕ ***
    @DeleteMapping("/{id}")                                                         // Запрос DELETE, в URL /4
    public void deleteAccount(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/login/{login}")                                                       // SQL ЗАПРОС
    public List<Account> getAccountLogin(@PathVariable String login){
        return service.getAccountLogin(login);
    }
}
