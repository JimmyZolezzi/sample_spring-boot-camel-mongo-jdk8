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

package com.ewerk.prototype.api;

import static com.jayway.restassured.RestAssured.expect;
import static org.assertj.core.api.Assertions.assertThat;

import com.ewerk.prototype.model.Person;
import com.ewerk.prototype.persistence.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 * Integration test class for the {@link PersonController} class.
 *
 * @author h.stolzenberg
 * @since 0.0.3
 */
public class PersonControllerIntegTest extends AbstractApiIntegTest {

  @SuppressWarnings("SpringJavaAutowiredMembersInspection")
  @Autowired
  private PersonRepository personRepository;

  @BeforeMethod
  public void setup() {
    personRepository.deleteAll();

    Person john = new Person();
    john.setLastName("Smith");
    john.setFirstName("John");
    john.setBirthday(LocalDate.of(2000, Month.DECEMBER, 10));
    personRepository.save(john);

    Person peggy = new Person();
    peggy.setLastName("Mullen");
    peggy.setFirstName("Peggy");
    peggy.setBirthday(LocalDate.of(1957, Month.APRIL, 16));
    personRepository.save(peggy);
  }

  @AfterMethod
  public void cleanup() {
    personRepository.deleteAll();
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testAll() throws Exception {
    //@formatter:off
    List<Person> persons =
      expect()
        .statusCode(200).and().contentType("application/json")
        .when()
          .get(url("/api/persons/all"))
          .as(List.class);
    //@formatter:on
    assertThat(persons).hasSize(2);
  }

  @Test
  public void testTruncate() throws Exception {
    Object response = expect().statusCode(200).get(url("/api/persons/truncate"));
    assertThat(response).isNotNull();
  }
}
