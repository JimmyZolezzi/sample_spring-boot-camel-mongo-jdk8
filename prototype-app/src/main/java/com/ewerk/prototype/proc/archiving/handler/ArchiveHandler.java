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

package com.ewerk.prototype.proc.archiving.handler;

import com.ewerk.prototype.model.Person;
import com.ewerk.prototype.persistence.repositories.PersonRepository;
import com.ewerk.prototype.proc.archiving.model.Archive;
import org.apache.camel.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Camel handler implementation that is the actual workhorse for doing the archiving task.
 *
 * @author h.stolzenberg
 * @since 0.0.4
 */
@Component
public class ArchiveHandler {
  private static final Logger LOG = LoggerFactory.getLogger(ArchiveHandler.class);

  @Autowired
  private PersonRepository personRepository;

  @Handler
  public void archive() {
    final List<Person> persons = personRepository.findAll();
    if (persons == null || persons.isEmpty()) {
      LOG.info("Nothing to backup");
      return;
    }

    LOG.info("Backup: {}", Archive.with(persons));
  }
}
