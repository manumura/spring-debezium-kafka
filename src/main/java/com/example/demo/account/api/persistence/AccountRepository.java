package com.example.demo.account.api.persistence;

import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface AccountRepository extends ListCrudRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByUuid(String uuid);
    Optional<AccountEntity> findByName(String name);
}
