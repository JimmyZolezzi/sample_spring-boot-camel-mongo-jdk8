package com.ewerk.prototype.api;

import static com.google.common.base.Preconditions.checkArgument;

import com.ewerk.prototype.model.Person;
import com.ewerk.prototype.persistence.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Creates a REST endpoint at the following URI:<br/><br/>
 *
 * <code>http://[host]:[port]/[contextPath]/api/persons</code><br/><br/>
 *
 * The controller provides access to the data within the MongoDB.
 *
 * @author h.stolzenberg
 * @since 0.0.3
 */
@RestController
@RequestMapping("/api/persons")
public class PersonController {
  private static final Logger LOG = LoggerFactory.getLogger(LoggerFactory.class);

  @Autowired
  private PersonRepository personRepository;

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public List<Person> findAll() {
    return personRepository.findAll();
  }

  @RequestMapping(value = "/create/{lastName}/{firstName}", method = RequestMethod.GET)
  public ResponseEntity<Person> create(@PathVariable final String lastName,
    @PathVariable final String firstName) {
    checkArgument(lastName != null && !lastName.isEmpty(),
      "The argument 'lastName' must not be null or empty.");
    checkArgument(firstName != null && !firstName.isEmpty(),
      "The argument 'firstName' must not be null or empty.");

    // TODO h.stolzenberg: only store person if not already present, but first integrate QueryDSL
    final Person person = new Person();
    person.setLastName(lastName);
    person.setFirstName(firstName);

    Person saved = personRepository.save(person);
    return new ResponseEntity<>(saved, HttpStatus.OK);
  }

  @RequestMapping(value = "/truncate", method = RequestMethod.GET)
  public void truncate() {
    personRepository.deleteAll();
    LOG.info("Truncated all persons");
  }
}
