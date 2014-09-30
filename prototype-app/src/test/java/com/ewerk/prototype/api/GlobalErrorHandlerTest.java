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

import static org.assertj.core.api.Assertions.assertThat;

import com.ewerk.prototype.AbstractUnitTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

public class GlobalErrorHandlerTest extends AbstractUnitTest {

  @Test
  public void testHandleException() {
    GlobalErrorHandler handler = new GlobalErrorHandler();
    final ResponseEntity<String> responseEntity =
      handler.handleException(new RuntimeException("Test"));
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    assertThat(responseEntity.getBody()).isEqualTo("Test");
  }
}