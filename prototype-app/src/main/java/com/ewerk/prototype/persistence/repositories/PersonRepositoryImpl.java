package com.ewerk.prototype.persistence.repositories;

import static com.google.common.base.Preconditions.checkArgument;

import com.ewerk.prototype.model.Person;
import com.ewerk.prototype.persistence.UniqueViolationException;
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

  @SuppressWarnings("ConstantConditions")
  @Override
  public Person create(Person person) {
    checkArgument(person != null, "The argument 'person' must not be null.");

    String lastName = person.getLastName();
    String firstName = person.getFirstName();

    if (personRepository.findByLastNameAndFirstName(lastName, firstName) != null) {
      throw new UniqueViolationException("Person '%s, %s' already exists.", lastName, firstName);
    }

    LOG.debug("Created: {}", person);
    return personRepository.save(person);
  }
}
