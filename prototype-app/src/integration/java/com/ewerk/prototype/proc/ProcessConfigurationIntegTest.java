/*
 * Copyright 2012-2014 the original author or authors.
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

package com.ewerk.prototype.proc;

import static org.assertj.core.api.Assertions.assertThat;

import com.ewerk.prototype.AbstractIntegTest;
import org.apache.camel.builder.RouteBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author holgerstolzenberg
 * @since 0.0.4
 */
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class ProcessConfigurationIntegTest extends AbstractIntegTest {
  @Autowired
  private RouteBuilder exportRouteBuilder;

  @Autowired
  private RouteBuilder archiveRouteBuilder;

  @Test
  public void testExportRouteBuilderPresent() {
    assertThat(exportRouteBuilder).isNotNull();
  }

  @Test
  public void testArchiveRouteBuilderPresent() {
    assertThat(archiveRouteBuilder).isNotNull();
  }
}
