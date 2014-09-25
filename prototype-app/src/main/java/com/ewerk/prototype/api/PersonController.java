package com.ewerk.prototype.api;

import com.ewerk.prototype.model.Person;
import com.ewerk.prototype.persistence.repositories.PersonRepository;
import com.ewerk.prototype.persistence.repositories.PersonRepositoryCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
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

  @Autowired
  private PersonRepositoryCustom personRepositoryCustom;

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public List<Person> findAll() {
    return personRepository.findAll();
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
  public ResponseEntity<Person> create(@RequestBody final Person person) {
    return new ResponseEntity<>(personRepositoryCustom.create(person), HttpStatus.OK);
  }

  @RequestMapping(value = "/truncate", method = RequestMethod.GET)
  public void truncate() {
    personRepository.deleteAll();
    LOG.info("Truncated all persons");
  }
}
