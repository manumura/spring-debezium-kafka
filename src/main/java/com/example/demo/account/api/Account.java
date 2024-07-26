package com.example.demo.account.api;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Account {
    private String uuid;
    private String name;
    private BigDecimal balance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer version;
}

// Cannot use record because of Redis serialization issue (@class property is not added to the JSON)
//public record Account(String uuid,
//                      String name,
//                      BigDecimal balance,
//                      LocalDateTime createdAt,
//                      LocalDateTime updatedAt,
//                      Integer version
//) {
//    public Account {
//        if (StringUtils.isBlank(name)) {
//            throw new IllegalArgumentException("Name must not be null or empty");
//        }
//        if (StringUtils.isBlank(uuid)) {
//            throw new IllegalArgumentException("UUID must not be null");
//        }
//        if (balance == null) {
//            throw new IllegalArgumentException("Balance must not be null");
//        }
//    }
//}
