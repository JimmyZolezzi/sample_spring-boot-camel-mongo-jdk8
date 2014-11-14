/*
 * Copyright 2012-2014 the original author or authors.
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

import static org.assertj.core.api.Assertions.assertThat;

import com.ewerk.prototype.AbstractIntegTest;
import com.ewerk.prototype.model.Person;
import com.ewerk.prototype.persistence.UniqueViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 * @author h.stolzenberg
 * @since 0.0.3
 */
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class PersonRepositoryCustomIntegTest extends AbstractIntegTest {
  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private PersonRepositoryCustom personRepositoryCustom;

  private Person john;

  @BeforeTest
  public void setup() {
    final LocalDate birthday = LocalDate.of(2000, Month.MARCH, 2);

    john = new Person();
    john.setLastName("Smith");
    john.setFirstName("John");
    john.setBirthday(birthday);
  }

  @Test
  public void testCreate() {
    personRepository.deleteAll();
    Person created = personRepositoryCustom.create(john);
    final List<Person> persons = personRepository.findByLastName("Smith");
    assertThat(persons).hasSize(1);
    assertThat(persons.get(0)).isEqualTo(created);
  }

  @Test(expectedExceptions = UniqueViolationException.class)
  public void testCreateFailsBecauseAlreadyPresent() {
    personRepository.deleteAll();
    Person created = personRepositoryCustom.create(john);
    final List<Person> persons = personRepository.findByLastName("Smith");
    assertThat(persons).hasSize(1);
    assertThat(persons.get(0)).isEqualTo(created);

    // 2nd persist should cause unique violation
    personRepositoryCustom.create(john);
  }
}
