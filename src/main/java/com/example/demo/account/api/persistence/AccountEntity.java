package com.example.demo.account.api.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table("accounts")
public record AccountEntity(@Id Long id,
                      String uuid,
                      String name,
                      BigDecimal balance,
                      @Column("created_at") LocalDateTime createdAt,
                      @Column("updated_at") LocalDateTime updatedAt,
                      @Version Integer version
) {}
