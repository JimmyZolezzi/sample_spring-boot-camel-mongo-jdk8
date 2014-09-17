package com.ewerk.prototype.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.ewerk.prototype.model.Person;
import com.ewerk.prototype.persistence.repositories.PersonRepository;
import com.ewerk.prototype.persistence.repositories.PersonRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Integration test class for the {@link PersonController} class.
 *
 * @author h.stolzenberg
 * @since 0.0.3
 */
public class PersonControllerIntegTest extends AbstractApiIntegTest {

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private PersonRepositoryCustom personRepositoryCustom;

  @BeforeMethod
  public void setup() {
    personRepository.deleteAll();
    personRepositoryCustom.create("Smith", "John");
    personRepositoryCustom.create("Mullen", "Peggy");
  }

  @AfterMethod
  public void cleanup() {
    personRepository.deleteAll();
  }

  @Test
  public void testAll() throws Exception {

    final String requestUrl = url("/api/persons/all");
    @SuppressWarnings("unchecked") List<Person> loaded =
      testRestTemplate().getForObject(requestUrl, List.class);
    assertThat(loaded).hasSize(2);
  }

  @Test
  public void testTruncate() throws Exception {
    final String requestUrl = url("/api/persons/truncate");
    assertThat(testRestTemplate().getForObject(requestUrl, Object.class)).isNull();
  }
}
