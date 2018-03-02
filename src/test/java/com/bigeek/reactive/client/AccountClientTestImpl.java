package com.bigeek.reactive.client;

import com.bigeek.reactive.dto.client.Account;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@Profile("test")
@Primary
@Service
public class AccountClientTestImpl implements AccountClient {

    public static final Account ACCOUNT_1 = Account.builder().accountId(1).personId(1).accountNumber("3781 457886 79824").balance(6100.49).build();
    public static final Account ACCOUNT_2 = Account.builder().accountId(2).personId(1).accountNumber("4532 5408 0495 8431").balance(1125.12).build();

    public static final List<Account> ACCOUNT_LIST = Arrays.asList(ACCOUNT_1, ACCOUNT_2);

    @Override
    public Flux<Account> getAccountsByPerson(Integer personId) {
        return Flux.fromIterable(ACCOUNT_LIST);
    }
}
