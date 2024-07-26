package com.example.demo.account.api;

import com.example.demo.account.api.dto.CreateAccountRequest;
import com.example.demo.account.api.dto.UpdateAccountBalanceRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping()
    public Account create(@RequestBody CreateAccountRequest request) {
        return accountService.create(request.name(), request.balance());
    }

    @GetMapping(
            value = "/{uuid}"
    )
    public Account getByUuid(@PathVariable UUID uuid) throws InterruptedException {
        return accountService.getByUuid(uuid.toString());
    }

    @GetMapping()
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @PatchMapping(
            value = "/{uuid}"
    )
    public Account updateBalanceByUuid(@PathVariable UUID uuid, @RequestBody UpdateAccountBalanceRequest request) {
        return accountService.updateBalanceByUuid(uuid, request.balance());
    }

    @DeleteMapping(
            value = "/{uuid}"
    )
    public Account deleteByUuid(@PathVariable UUID uuid) {
        return accountService.deleteByUuid(uuid.toString());
    }
}
