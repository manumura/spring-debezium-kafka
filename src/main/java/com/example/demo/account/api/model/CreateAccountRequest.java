package com.example.demo.account.api.model;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public record CreateAccountRequest(String name, BigDecimal balance) {
    public CreateAccountRequest {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }
        if (balance == null) {
            throw new IllegalArgumentException("Balance must not be null");
        }
    }
}
