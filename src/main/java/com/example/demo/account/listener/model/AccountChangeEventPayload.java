package com.example.demo.account.listener.model;

import com.example.demo.account.api.persistence.AccountEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AccountChangeEventPayload(

    @JsonProperty("op")
    String op,

    @JsonProperty("before")
    AccountEntity accountEntityBefore,

    @JsonProperty("after")
    AccountEntity accountEntityAfter
) {}
