package com.bigeek.reactive.handler;

import com.bigeek.reactive.client.AccountClient;
import com.bigeek.reactive.domain.Person;
import com.bigeek.reactive.dto.client.Account;
import com.bigeek.reactive.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * Handler for person requests.
 */
@Service
@Slf4j
public class PersonHandler {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private AccountClient accountClient;

    /**
     * Get the person with the path variable identifier.
     *
     * @param request service request.
     * @return a response with the person with that identifier or not found response.
     */
    public Mono<ServerResponse> getPerson(ServerRequest request) {
        int personId = Integer.parseInt(request.pathVariable("id"));
        log.debug("Getting person with id {}", personId);
        Mono<Person> personMono = this.repository.findById(personId);
        return personMono
                .flatMap(person -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(person)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * Create a person/persons with values passed in request body.
     *
     * @param request service request.
     * @return a response with the created person/persons.
     */
    public Mono<ServerResponse> createPerson(ServerRequest request) {
        log.info("Creating people");
        // Log the flux body received.
        Flux<Person> person = request.bodyToFlux(Person.class).log();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(repository.saveAll(person), Person.class);
    }

    /**
     * Return all people in DB.
     *
     * @param request service request.
     * @return a response with all the people.
     */
    public Mono<ServerResponse> listPeople(ServerRequest request) {
        log.debug("Getting all people");
        Flux<Person> people = this.repository.findAll();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(people, Person.class);
    }

    /**
     * Get the accounts of person with the path variable identifier.
     *
     * @param request service request.
     * @return a response with the person with that identifier and his/her accounts, or not found response.
     */
    public Mono<ServerResponse> getPersonAccounts(ServerRequest request) {
        int personId = Integer.parseInt(request.pathVariable("id"));
        log.debug("Getting person with accounts with id {}", personId);
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(accountClient.getAccountsByPerson(personId), Account.class);
    }

}