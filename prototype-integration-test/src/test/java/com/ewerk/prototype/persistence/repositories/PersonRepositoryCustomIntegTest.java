package com.ewerk.prototype.persistence.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.ewerk.prototype.AbstractIntegTest;
import com.ewerk.prototype.model.Person;
import com.ewerk.prototype.persistence.UniqueViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author h.stolzenberg
 * @since 0.0.3
 */
public class PersonRepositoryCustomIntegTest extends AbstractIntegTest {
  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private PersonRepositoryCustom personRepositoryCustom;

  @Test
  public void testCreate() {
    personRepository.deleteAll();
    Person created = personRepositoryCustom.create("Smith", "John");
    final List<Person> persons = personRepository.findByLastName("Smith");
    assertThat(persons).hasSize(1);
    assertThat(persons.get(0)).isEqualTo(created);
  }

  @Test(expectedExceptions = UniqueViolationException.class)
  public void testCreateFailsBecauseAlreadyPresent() {
    personRepository.deleteAll();
    Person created = personRepositoryCustom.create("Smith", "John");
    final List<Person> persons = personRepository.findByLastName("Smith");
    assertThat(persons).hasSize(1);
    assertThat(persons.get(0)).isEqualTo(created);

    personRepositoryCustom.create("Smith", "John");
  }
}
