package com.ewerk.prototype.persistence.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.ewerk.prototype.AbstractIntegTest;
import com.ewerk.prototype.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * @author h.stolzenberg
 * @since 0.0.3
 */
public class PersonRepositoryIntegTest extends AbstractIntegTest {

  @Autowired
  private PersonRepository personRepository;

  @Test
  public void testCrudOperations() {
    personRepository.deleteAll();

    Person person = new Person();
    person.setFirstName("John");
    person.setLastName("Smith");

    Person saved = personRepository.save(person);
    assertThat(saved.getId()).isNotEmpty();

    Person loaded = personRepository.findOne(saved.getId());
    assertThat(loaded.getId()).isEqualTo(saved.getId());
    assertThat(loaded.getFirstName()).isEqualTo(saved.getFirstName());
    assertThat(loaded.getLastName()).isEqualTo(saved.getLastName());
  }
}
