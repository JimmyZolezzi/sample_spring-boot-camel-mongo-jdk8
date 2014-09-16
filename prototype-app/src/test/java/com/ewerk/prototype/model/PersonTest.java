package com.ewerk.prototype.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.ewerk.prototype.AbstractUnitTest;
import org.testng.annotations.Test;

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
  public void testToString() {
    Person person = new Person();
    person.setId("1s3e");
    person.setFirstName("John");
    person.setLastName("Smith");

    assertThat(person.toString()).isEqualTo("Person{id=1s3e, lastName=Smith, firstName=John}");
  }
}