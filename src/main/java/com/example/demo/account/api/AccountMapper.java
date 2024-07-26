package com.example.demo.account.api;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toAccount(AccountEntity accountEntity);

    List<Account> toAccounts(List<AccountEntity> accountEntities);
}
