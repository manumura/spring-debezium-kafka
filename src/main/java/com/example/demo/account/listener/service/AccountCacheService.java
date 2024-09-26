package com.example.demo.account.listener.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class AccountCacheService {

    private static final Logger log = LoggerFactory.getLogger(AccountCacheService.class);

    @CacheEvict(value = "account", cacheManager = "redisCacheManager")
    public void cacheEvict(String uuid) {
        log.info("Evicting account from cache with uuid: {}", uuid);
        // Nothing to do
    }

}
