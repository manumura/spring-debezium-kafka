package com.example.demo.account.listener.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AccountChangeEvent(
    @JsonProperty("payload")
    AccountChangeEventPayload payload
) {}
