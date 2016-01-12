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

package com.ewerk.prototype.proc.export.handler;

import static org.slf4j.LoggerFactory.getLogger;

import com.ewerk.prototype.persistence.repositories.PersonRepository;
import org.apache.camel.Handler;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This camel handler is the actual workhorse of the export camel route. It takes the given {@link
 * com.ewerk.prototype.model.Person} and writes its JSON representation to the file system.
 *
 * @author holgerstolzenberg
 * @since 0.0.4
 */
@Component
public class ExportHandler {
  private static final Logger LOG = getLogger(ExportHandler.class);

  @Autowired
  private PersonRepository personRepository;

  @Handler
  public void export() {
    // TODO h.stolzenberg: do the real export stuff
    LOG.info("Exporting data ...");
    personRepository.findAll().stream().parallel().forEach(person -> LOG.debug("{}", person));
  }
}
