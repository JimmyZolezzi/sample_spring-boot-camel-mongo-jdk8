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

import com.ewerk.prototype.AbstractIntegTest;
import com.jayway.restassured.RestAssured;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.BeforeClass;

/**
 * This is the base class for all integration tests that work with the REST API. Some accessor
 * methods provide easy access to the embedded servlet containers setup. Please note that we create
 * the embedded container with a random port number, which then is retrieved from the spring
 * context.
 *
 * @author h.stolzenberg
 * @since 0.0.3
 */
abstract class AbstractApiIntegTest extends AbstractIntegTest {

  @Value("${server.context-path}")
  private String contextPath;

  @Value("${local.server.port}")
  private int port;

  @BeforeClass
  public void setupRestAssured() {
    RestAssured.port = port();
  }

  protected final int port() {
    return port;
  }

  protected final String contextPath() {
    return contextPath;
  }

  protected final String url(String path) {
    String tmp = path != null ? path : "";
    if (!tmp.startsWith("/")) {
      tmp = "/" + tmp;
    }
    final String url = contextPath() + tmp;
    LOG.debug("Url: {}", url);
    return url;
  }
}
