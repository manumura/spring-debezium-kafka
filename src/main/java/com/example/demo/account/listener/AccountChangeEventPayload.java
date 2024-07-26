package com.example.demo.account.listener;

import com.example.demo.account.api.AccountEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AccountChangeEventPayload(

    @JsonProperty("op")
    String op,

    @JsonProperty("before")
    AccountEntity accountEntityBefore,

    @JsonProperty("after")
    AccountEntity accountEntityAfter
) {}
