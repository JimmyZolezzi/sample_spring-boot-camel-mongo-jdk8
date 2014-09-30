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

import static org.assertj.core.api.Assertions.assertThat;

import com.ewerk.prototype.AbstractUnitTest;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;

public class PersonTest extends AbstractUnitTest {
  @Test
  public void testSetGetId() {
    final String id = "2s3e4";
    Person person = new Person();
    person.setId(id);
    assertThat(person.getId()).isEqualTo(id);
  }

  @Test
  public void testSetGetLastName() {
    final String lastName = "2s3e4";
    Person person = new Person();
    person.setLastName(lastName);
    assertThat(person.getLastName()).isEqualTo(lastName);
  }

  @Test
  public void testSetGetFirstName() {
    final String lastName = "2s3e4";
    Person person = new Person();
    person.setFirstName(lastName);
    assertThat(person.getFirstName()).isEqualTo(lastName);
  }

  @Test
  public void testSetGetBirthday() {
    final LocalDate now = LocalDate.now();
    Person person = new Person();
    person.setBirthday(now);
    assertThat(person.getBirthday()).isEqualTo(now);
  }

  @Test
  public void testNotEqualThroughFirstName() {
    final LocalDate now = LocalDate.now();

    Person dave = new Person();
    dave.setFirstName("Dave");
    dave.setLastName("Matthews");
    dave.setBirthday(now);

    Person david = new Person();
    david.setFirstName("David");
    david.setLastName("Matthews");
    david.setBirthday(now);

    assertThat(dave).isNotEqualTo(david);
  }

  @Test
  public void testNotEqualThroughLastName() {
    final LocalDate now = LocalDate.now();

    Person daveA = new Person();
    daveA.setFirstName("Dave");
    daveA.setLastName("Matthews");
    daveA.setBirthday(now);

    Person daveB = new Person();
    daveB.setFirstName("Dave");
    daveB.setLastName("Morris");
    daveB.setBirthday(now);

    assertThat(daveA).isNotEqualTo(daveB);
  }

  @Test
  public void testNotEqualByThroughBirthday() {
    Person daveA = new Person();
    daveA.setFirstName("Dave");
    daveA.setLastName("Matthews");
    daveA.setBirthday(LocalDate.of(2000, Month.DECEMBER, 1));

    Person daveB = new Person();
    daveB.setFirstName("Dave");
    daveB.setLastName("Matthews");
    daveB.setBirthday(LocalDate.of(2000, Month.NOVEMBER, 1));

    assertThat(daveA).isNotEqualTo(daveB);
  }

  @Test
  public void testEqualityAndHashCode() {
    final LocalDate birthday = LocalDate.of(2000, Month.DECEMBER, 1);
    final String firstName = "Dave";
    final String lastName = "Matthews";

    Person daveA = new Person();
    daveA.setFirstName(firstName);
    daveA.setLastName(lastName);
    daveA.setBirthday(birthday);

    Person daveB = new Person();
    daveB.setFirstName(firstName);
    daveB.setLastName(lastName);
    daveB.setBirthday(birthday);

    assertThat(daveA.equals(daveB)).isTrue();
    assertThat(daveA.hashCode()).isEqualTo(daveB.hashCode());
  }

  @Test
  public void testToString() {
    LocalDate birthday = LocalDate.of(2014, Month.APRIL, 1);

    Person person = new Person();
    person.setId("1s3e");
    person.setFirstName("John");
    person.setLastName("Smith");
    person.setBirthday(birthday);

    assertThat(person.toString()).isEqualTo(
      "Person{id=1s3e, lastName=Smith, firstName=John, birthday=2014-04-01}");
  }
}