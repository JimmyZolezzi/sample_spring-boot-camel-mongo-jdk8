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

package com.ewerk.prototype;

import com.ewerk.prototype.api.ApiConfiguration;
import com.ewerk.prototype.persistence.PersistenceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Integration test base class. Configures the spring-test stuff. A think to remark here is that we
 * need to declare the {@link TestExecutionListeners} more explicitly because we are using
 * TestNG.<br/><br/>
 *
 * TestNG requires us to extend from {@link AbstractTestNGSpringContextTests} which also tries to
 * provide a mock servlet context, but that conflicts with {@link SpringApplicationConfiguration}
 * which is by default JUnit based.<br/><br/>
 *
 * See http://stackoverflow.com/questions/25537436/integration-testing-a-spring-boot-web-app-with-testng
 * <br/><br/>
 *
 * For another good example have a look at:<br/> http://www.jayway.com/2014/07/04/integration-testing-a-spring-boot-application/<br/><br/>
 *
 * In addition we activate the profile 'integration-test' in order to define property overrides for
 * the integration tests, so that we can run our tests against a different database.
 *
 * @author h.stolzenberg
 * @see org.springframework.test.context.ActiveProfiles
 * @see org.springframework.test.context.TestExecutionListeners
 * @see org.springframework.test.context.testng.AbstractTestNGSpringContextTests
 * @see org.springframework.test.context.web.WebAppConfiguration
 * @see org.springframework.boot.test.IntegrationTest
 * @see org.springframework.boot.test.SpringApplicationConfiguration
 * @since 0.0.3
 */
@ActiveProfiles({"default", "integration-test"})
@SpringApplicationConfiguration(
  classes = {PrototypeApplication.class, PersistenceConfiguration.class, ApiConfiguration.class},
  initializers = {ConfigFileApplicationContextInitializer.class})
@TestExecutionListeners(inheritListeners = false,
  listeners = {DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class})
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public abstract class AbstractIntegTest extends AbstractTestNGSpringContextTests {

  protected static final Logger LOG = LoggerFactory.getLogger(AbstractIntegTest.class);

  @Autowired
  private Environment environment;

  /**
   * Convenient accessor method. Provides the spring environment configuration.
   *
   * @return The {@link org.springframework.core.env.Environment} of the tests
   */
  protected Environment environment() {
    return environment;
  }
}
