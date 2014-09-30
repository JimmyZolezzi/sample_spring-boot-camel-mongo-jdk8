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

package com.ewerk.prototype.proc.archiving.model;

import static com.google.common.base.Preconditions.checkArgument;

import com.ewerk.prototype.model.Person;
import com.google.auto.value.AutoValue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * Simple bean that holds all necessary information of a person backup archive.
 *
 * @author h.stolzenberg
 * @since 0.0.5
 */
@AutoValue
public abstract class Archive {
  public abstract String uid();

  public abstract List<Person> archivedPersons();

  public abstract LocalDateTime creation();

  Archive() {
  }

  @Nonnull
  public static Archive with(@CheckForNull final List<Person> persons) {
    checkArgument(persons != null && !persons.isEmpty(),
      "Argument 'persons' must not be null or empty.");
    return new AutoValue_Archive(UUID.randomUUID().toString(), persons, LocalDateTime.now());
  }
}
