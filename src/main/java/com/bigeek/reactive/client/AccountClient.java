package com.bigeek.reactive.client;

import com.bigeek.reactive.dto.client.Account;
import reactor.core.publisher.Flux;

/**
 * Account client interface.
 */
public interface AccountClient {

    Flux<Account> getAccountsByPerson(Integer personId);
}
