package com.ewerk.prototype.persistence.repositories;

import com.ewerk.prototype.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Simple spring-data CRUD repository for interacting with the MongoDB {@link Person} document
 * store.
 *
 * @author h.stolzenberg
 * @see org.springframework.data.mongodb.repository.MongoRepository
 * @since 0.0.1
 */
public interface PersonRepository extends MongoRepository<Person, String> {

  public List<Person> findByLastName(String lastName);

  public Person findByLastNameAndFirstName(String lastName, String firstName);
}
