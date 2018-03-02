package com.bigeek.reactive;

import com.bigeek.reactive.handler.PersonHandler;
import com.bigeek.reactive.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.bigeek.reactive.constants.Persons.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Reactive service to perform person operations.
 */
@SpringBootApplication
@Slf4j
public class RxPersonServiceApplication implements CommandLineRunner {

    @Value("${client.account.url}")
    private String clientUrl;

    @Autowired
    private PersonRepository personRepository;

    /**
     * This bean is a routerFunction which maps the invoked resource to its correspondent handler function.
     * Either it has a filter to log requests to the service.
     *
     * @param personHandler handler for the resources.
     * @return a router function.
     */
    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(PersonHandler personHandler) {
        return nest(path("/person"),
                nest(accept(APPLICATION_JSON),
                        route(GET("/{id}/account"), personHandler::getPersonAccounts)
                                .andRoute(GET("/{id}"), personHandler::getPerson)
                                .andRoute(method(HttpMethod.GET), personHandler::listPeople)
                ).andRoute(POST("/").and(contentType(APPLICATION_JSON)), personHandler::createPerson)
                        .filter((serverRequest, handlerFunction) -> {
                            log.debug("Requested {} {}", serverRequest.method(), serverRequest.path());
                            return handlerFunction.handle(serverRequest);
                        }));
    }

    /**
     * This bean is the WebClient initialized with the base url of service to call.
     *
     * @return webclient.
     */
    @Bean
    public WebClient getWebClient() {
        return WebClient.builder().defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(clientUrl).build();
    }

    public static void main(String[] args) {
        SpringApplication.run(RxPersonServiceApplication.class);
    }

    /**
     * Initialize the DB inserting some people.
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        personRepository.deleteAll().block();

        personRepository.save(PERSON_1).block();
        personRepository.save(PERSON_2).block();
        personRepository.save(PERSON_3).block();
        personRepository.save(PERSON_4).block();
    }
}
