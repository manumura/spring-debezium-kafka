package com.example.demo.account.api.dto;

import java.math.BigDecimal;

public record UpdateAccountBalanceRequest(BigDecimal balance) {
    public UpdateAccountBalanceRequest {
        if (balance == null) {
            throw new IllegalArgumentException("Balance must not be null");
        }
    }
}
