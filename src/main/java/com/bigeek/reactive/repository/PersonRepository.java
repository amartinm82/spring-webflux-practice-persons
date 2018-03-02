package com.bigeek.reactive.repository;

import com.bigeek.reactive.domain.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Reactive MongoDB repository.
 */
@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person, Integer> {
}
