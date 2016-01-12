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

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Objects.equal;
import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Simple entity bean representing a person.
 *
 * @author holgerstolzenberg
 * @since 0.0.2
 */
@Document
public final class Person {
  @Id
  private String id;

  private String firstName;

  @Indexed
  private String lastName;

  @Indexed
  @DateTimeFormat(iso = DATE)
  private LocalDate birthday;

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(final LocalDate birthday) {
    this.birthday = birthday;
  }

  @SuppressWarnings("NonFinalFieldReferenceInEquals")
  @Override
  public boolean equals(final Object o) {
    if (this == o)
      return true;
    if ((o == null) || (getClass() != o.getClass()))
      return false;
    final Person person = (Person) o;
    return equal(id, person.id) &&
        equal(firstName, person.firstName) &&
        equal(lastName, person.lastName) &&
        equal(birthday, person.birthday);
  }

  @SuppressWarnings("NonFinalFieldReferencedInHashCode")
  @Override
  public int hashCode() {
    return Objects.hashCode(id, firstName, lastName, birthday);
  }

  @Override
  public String toString() {
    return toStringHelper(this).add("id", id)
        .add("lastName", lastName)
        .add("firstName", firstName)
        .add("birthday", birthday)
        .toString();
  }
}
