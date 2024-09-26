package com.example.demo.account.api.mapper;

import com.example.demo.account.api.model.Account;
import com.example.demo.account.api.persistence.AccountEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toAccount(AccountEntity accountEntity);

    List<Account> toAccounts(List<AccountEntity> accountEntities);
}
