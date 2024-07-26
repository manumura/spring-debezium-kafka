package com.example.demo.account.listener;

public enum OperationType {

    CREATE("c"),
    UPDATE("u"),
    DELETE("d");

    private final String code;

    OperationType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
