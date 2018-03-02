package com.bigeek.reactive;

import com.bigeek.reactive.domain.Person;
import com.bigeek.reactive.dto.client.Account;
import com.bigeek.reactive.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

import static com.bigeek.reactive.client.AccountClientTestImpl.ACCOUNT_1;
import static com.bigeek.reactive.client.AccountClientTestImpl.ACCOUNT_2;
import static com.bigeek.reactive.constants.Persons.*;

/**
 * Application test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RxPersonServiceApplicationTest {

    @Autowired
    private WebTestClient testClient;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void getPerson() throws Exception {
        FluxExchangeResult<Person> result = this.testClient.get()
                .uri("/person/1")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .returnResult(Person.class);

        StepVerifier.create(result.getResponseBody())
                .expectNext(PERSON_1)
                .expectComplete()
                .verify();
    }

    @Test
    public void getPersonNotFound() throws Exception {
        this.testClient.get()
                .uri("/person/42")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void listPeople() throws Exception {
        FluxExchangeResult<Person> result = this.testClient.get()
                .uri("/person/")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .returnResult(Person.class);

        StepVerifier.create(result.getResponseBody())
                .expectNext(PERSON_1, PERSON_2, PERSON_3, PERSON_4)
                .expectComplete()
                .verify();
    }

    @Test
    public void createPerson() throws Exception {
        FluxExchangeResult<Person> result = this.testClient.post()
                .uri("/person/")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(PERSON_5)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Person.class);

        StepVerifier.create(result.getResponseBody())
                .expectNext(PERSON_5)
                .expectComplete()
                .verify();

        personRepository.delete(PERSON_5);

    }

    @Test
    public void getPersonAccounts() throws Exception {

        FluxExchangeResult<Account> result = this.testClient.get()
                .uri("/person/1/account")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .returnResult(Account.class);

        StepVerifier.create(result.getResponseBody())
                .expectNext(ACCOUNT_1, ACCOUNT_2)
                .expectComplete()
                .verify();
    }
}
