package com.ewerk.prototype.api;

import static com.jayway.restassured.RestAssured.expect;
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
