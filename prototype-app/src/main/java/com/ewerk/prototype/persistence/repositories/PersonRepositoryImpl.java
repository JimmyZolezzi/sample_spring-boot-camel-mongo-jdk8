package com.ewerk.prototype.persistence.repositories;

import static com.google.common.base.Preconditions.checkArgument;

import com.ewerk.prototype.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Default implementation of the custom spring-data repository of the {@link Person} entity.
 *
 * @author h.stolzenberg
 * @since 0.0.3
 */
@Repository
public class PersonRepositoryImpl implements PersonRepositoryCustom {

  private static final Logger LOG = LoggerFactory.getLogger(LoggerFactory.class);

  @Autowired
  private PersonRepository personRepository;

  @Override
  public Person create(final String lastName, final String firstName) {
    checkArgument(lastName != null && !lastName.isEmpty(),
      "The argument 'lastName' must not be null or empty.");
    checkArgument(firstName != null && !firstName.isEmpty(),
      "The argument 'firstName' must not be null or empty.");

    final Person person = new Person();
    person.setLastName(lastName);
    person.setFirstName(firstName);

    LOG.debug("Created: {}", person);

    return personRepository.save(person);
  }
}
