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

  private String firstName;

  @Indexed
  private String lastName;

  public Person() {
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

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("id", id)
      .add("lastName", lastName)
      .add("firstName", firstName)
      .toString();
  }
}
