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

import static com.google.common.base.Preconditions.checkArgument;

import com.ewerk.prototype.model.Person;
import com.ewerk.prototype.persistence.UniqueViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;

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
