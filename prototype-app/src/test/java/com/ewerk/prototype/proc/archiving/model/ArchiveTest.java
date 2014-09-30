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

import static org.assertj.core.api.Assertions.assertThat;

import com.ewerk.prototype.AbstractUnitTest;
import com.ewerk.prototype.model.Person;
import com.google.common.collect.Lists;
import org.testng.annotations.Test;

public class ArchiveTest extends AbstractUnitTest {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testWithNullPersons() {
    Archive.with(null);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testWithEmptyPersons() {
    Archive.with(Lists.newArrayList());
  }

  @Test
  public void testWith() {
    final Person person = new Person();
    final Archive archive = Archive.with(Lists.newArrayList(person));
    assertThat(archive.uid()).isNotEmpty();
    assertThat(archive.archivedPersons().get(0)).isEqualTo(person);
    assertThat(archive.creation()).isNotNull();
  }
}