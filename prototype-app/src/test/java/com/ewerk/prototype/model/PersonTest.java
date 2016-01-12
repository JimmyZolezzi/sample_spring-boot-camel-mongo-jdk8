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

package com.ewerk.prototype.model;

import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;

import com.ewerk.prototype.AbstractUnitTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;

public class PersonTest extends AbstractUnitTest {
  private static final String ID = "SJ";
  private static final String LAST_NAME = "Smith";
  private static final String FIRST_NAME = "John";
  private static final LocalDate BIRTHDAY = of(2014, Month.APRIL, 1);

  private Person person;

  @BeforeMethod
  public void setup() {
    person = new Person();
    person.setId(ID);
    person.setLastName(LAST_NAME);
    person.setFirstName(FIRST_NAME);
    person.setBirthday(BIRTHDAY);
  }

  @Test
  public void testGetId() {
    assertThat(person.getId()).isEqualTo(ID);
  }

  @Test
  public void testGetLastName() {
    assertThat(person.getLastName()).isEqualTo(LAST_NAME);
  }

  @Test
  public void testGetFirstName() {
    assertThat(person.getFirstName()).isEqualTo(FIRST_NAME);
  }

  @Test
  public void testGetBirthday() {
    assertThat(person.getBirthday()).isEqualTo(BIRTHDAY);
  }

  @Test
  public void testToString() {
    assertThat(person.toString()).isEqualTo(
        "Person{id=SJ, lastName=Smith, firstName=John, birthday=2014-04-01}");
  }
}