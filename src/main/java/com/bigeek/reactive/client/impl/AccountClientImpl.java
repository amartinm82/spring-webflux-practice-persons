package com.bigeek.reactive.client.impl;

import com.bigeek.reactive.client.AccountClient;
import com.bigeek.reactive.dto.client.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * Account client. Connect to account service.
 */
@Service
@Slf4j
public class AccountClientImpl implements AccountClient {

    @Autowired
    private WebClient webClient;

    /**
     * Get the accounts that belongs to a person.
     *
     * @param personId person identifier.
     * @return Flux of person accounts.
     */
    @Override
    public Flux<Account> getAccountsByPerson(Integer personId) {
        log.debug("Calling account client to get accounts for personId {}", personId);
        return this.webClient.get().uri("/person/{personId}", personId)
                .retrieve().bodyToFlux(Account.class);
    }

}
