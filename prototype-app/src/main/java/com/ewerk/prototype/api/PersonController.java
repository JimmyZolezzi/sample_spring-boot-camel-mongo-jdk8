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

package com.ewerk.prototype.api;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.ewerk.prototype.model.Person;
import com.ewerk.prototype.persistence.repositories.PersonRepository;
import com.ewerk.prototype.persistence.repositories.PersonRepositoryCustom;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Nonnull;

/**
 * Creates a REST endpoint at the following URI:<br/><br/>
 * {@code http://[host]:[port]/[contextPath]/api/persons}<br/><br/>
 * The controller provides access to the data within the MongoDB.
 *
 * @author holgerstolzenberg
 * @since 0.0.3
 */
@RestController
@RequestMapping("/api/persons")
public class PersonController {
  private static final Logger LOG = getLogger(PersonController.class);

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private PersonRepositoryCustom personRepositoryCustom;

  @RequestMapping(value = "/all", method = GET)
  public List<Person> findAll() {
    return personRepository.findAll();
  }

  @RequestMapping(value = "/create", method = POST, consumes = "application/json")
  public ResponseEntity<Person> create(@Nonnull @RequestBody final Person person) {
    return new ResponseEntity<>(personRepositoryCustom.create(person), OK);
  }

  @RequestMapping(value = "/truncate", method = GET)
  public void truncate() {
    personRepository.deleteAll();
    LOG.info("Truncated all persons");
  }
}
