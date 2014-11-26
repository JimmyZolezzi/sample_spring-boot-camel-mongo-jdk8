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

package com.ewerk.prototype.persistence.repositories;

import com.ewerk.prototype.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

import javax.annotation.Nonnull;

/**
 * Simple spring-data CRUD repository for interacting with the MongoDB {@link Person} document
 * store.
 *
 * @author holgerstolzenberg
 * @see org.springframework.data.mongodb.repository.MongoRepository
 * @since 0.0.1
 */
public interface PersonRepository extends MongoRepository<Person, String> {

  public List<Person> findByLastName(@Nonnull String lastName);

  public Person findByLastNameAndFirstName(@Nonnull String lastName, @Nonnull String firstName);
}
