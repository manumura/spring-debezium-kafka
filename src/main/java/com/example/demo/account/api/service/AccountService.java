package com.example.demo.account.api.service;

import com.example.demo.account.api.mapper.AccountMapper;
import com.example.demo.account.api.model.Account;
import com.example.demo.account.api.persistence.AccountEntity;
import com.example.demo.account.api.persistence.AccountRepository;
import com.example.demo.exception.EntityExistsException;
import com.example.demo.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Cacheable(value = "account", cacheManager = "redisCacheManager")
    public Account getByUuid(String uuid) throws InterruptedException {
        var accountEntity = accountRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Account Not Found!"));

        // Simulate slow response
        Thread.sleep(3000);

        log.info("Account found: {}", accountEntity);
        return accountMapper.toAccount(accountEntity);
    }

    public Account getById(Long id) {
        var accountEntity = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account Not Found!"));
        return accountMapper.toAccount(accountEntity);
    }

    public List<Account> findAll() {
        var accountEntities = accountRepository.findAll();
        return accountMapper.toAccounts(accountEntities);
    }

    @Transactional
    public Account create(String name, BigDecimal balance) {
        var accountOptional = accountRepository.findByName(name);
        if (accountOptional.isPresent()) {
            throw new EntityExistsException(String.format("\"Account with name %s already exists!", name));
        }

        var uuid = UUID.randomUUID();
        var accountEntityToCreate = new AccountEntity(null, uuid.toString(), name, balance, LocalDateTime.now(), LocalDateTime.now(), null);
        var accountEntity = accountRepository.save(accountEntityToCreate);
        log.info("Account created: {}", accountEntity);
        return accountMapper.toAccount(accountEntity);
    }

    @Transactional
    public Account updateBalanceByUuid(UUID uuid, BigDecimal balance) {
        var accountEntity = accountRepository.findByUuid(uuid.toString())
                .orElseThrow(() -> new EntityNotFoundException("Account Not Found!"));

        var accountEntityToUpdate = new AccountEntity(accountEntity.id(), accountEntity.uuid(), accountEntity.name(), balance, accountEntity.createdAt(), LocalDateTime.now(), accountEntity.version());
        var accountEntityUpdated = accountRepository.save(accountEntityToUpdate);
        log.info("Account updated: {}", accountEntityUpdated);
        return accountMapper.toAccount(accountEntityUpdated);
    }

    @CacheEvict(value = "account", cacheManager = "redisCacheManager")
    @Transactional
    public Account deleteByUuid(String uuid) {
        var accountEntity = accountRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Account Not Found!"));

        accountRepository.delete(accountEntity);
        log.info("Account deleted: {}", accountEntity);
        return accountMapper.toAccount(accountEntity);
    }
}
