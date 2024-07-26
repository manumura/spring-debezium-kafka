package com.example.demo.account.listener;

import com.example.demo.account.api.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class AccountChangeListener {

    private static final Logger log = LoggerFactory.getLogger(AccountChangeListener.class);

    private final AccountService accountService;
    private final AccountCacheService accountCacheService;

    public AccountChangeListener(AccountService accountService, AccountCacheService accountCacheService) {
        this.accountService = accountService;
        this.accountCacheService = accountCacheService;
    }

    @KafkaListener(topics = "demo-debezium.public.accounts", containerFactory = "kafkaListenerDebezium", groupId = "kafkaListenerDebezium")
    public void debeziumListener(@Payload(required = false) AccountChangeEvent event) {
        log.info("--- Event received --- {}", event);
        if (event == null || event.payload() == null) {
            log.warn("Event or event payload is null");
            return;
        }

        var payload = event.payload();
        var op = payload.op();

        try {
            if (OperationType.CREATE.getCode().equals(op)) {
                log.info("--- Create event --- {}", event.payload());
            } else if (OperationType.UPDATE.getCode().equals(op)) {
                log.info("--- Update event --- {}", event.payload());
                accountCacheService.cacheEvict(event.payload().accountEntityAfter().uuid());
            } else if (OperationType.DELETE.getCode().equals(op)) {
                log.info("--- Delete event --- {}", event.payload());
                // Delete event returns only ID of the entity
            } else{
                // do nothing
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
