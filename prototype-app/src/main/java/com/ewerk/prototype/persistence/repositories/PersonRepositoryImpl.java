/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ewerk.prototype.persistence.repositories;

import static com.ewerk.prototype.model.QPerson.person;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import static org.slf4j.LoggerFactory.getLogger;

import com.ewerk.prototype.model.Person;
import com.ewerk.prototype.persistence.UniqueViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Default implementation of the custom spring-data repository of the {@link Person} entity.
 *
 * @author holgerstolzenberg
 * @since 0.0.3
 */
@Component
public class PersonRepositoryImpl implements PersonRepositoryCustom {

  private static final Logger LOG = getLogger(LoggerFactory.class);

  @Autowired
  private PersonRepository personRepository;

  @SuppressWarnings("ConstantConditions")
  @Override
  public Person create(final Person person) {
    checkArgument(person != null, "The argument 'person' must not be null.");

    final String lastName = person.getLastName();
    final String firstName = person.getFirstName();

    if (personRepository.findByLastNameAndFirstName(lastName, firstName) != null) {
      throw new UniqueViolationException("Person '%s, %s' already exists.", lastName, firstName);
    }

    LOG.debug("Created: {}", person);
    return personRepository.save(person);
  }

  @Override
  public Iterable<Person> locate(final String lastName, final String firstName) {
    checkArgument(!isNullOrEmpty(lastName));
    checkArgument(!isNullOrEmpty(firstName));

    return personRepository.findAll(
        person.lastName.eq(lastName).and(person.firstName.eq(firstName)));
  }
}
