package com.ewerk.prototype.model;

import com.google.common.base.MoreObjects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Simple entity bean representing a person.
 *
 * @author h.stolzenberg
 * @since 0.0.2
 */
@Document
public class Person {

  @Id
  private String id;

  private final String firstName;

  @Indexed
  private final String lastName;

  public Person(final String firstName, final String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("id", id)
      .add("firstName", firstName)
      .add("lastName", lastName)
      .toString();
  }
}
