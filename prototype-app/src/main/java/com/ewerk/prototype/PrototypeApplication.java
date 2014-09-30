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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * This is the application entry point. Utilizing the main method makes it easy to run/debug the
 * application from CLI oder IDE. In the Spring samples this class is often annotated with {@link
 * org.springframework.context.annotation.Configuration} and is therefore used as bean factory. We
 * do not go this way in this example, as we want to use more purpose specific configuration
 * classes.<br/><br/>
 *
 * The app registers a {@link org.springframework.boot.actuate.system.ApplicationPidListener} and
 * writes a PID file {@link PrototypeApplication#PID_FILE} to the current directory. The path is
 * currently not configurable through spring-boot itself.
 *
 * @author h.stolzenberg
 * @since 0.0.1
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class PrototypeApplication {

  private static final Logger LOG = LoggerFactory.getLogger(LoggerFactory.class);

  private static final String PID_FILE = "prototype.pid";

  public static void main(String[] args) {
    SpringApplication application = new SpringApplication(PrototypeApplication.class);
    application.setHeadless(true);
    application.setRegisterShutdownHook(true);
    application.setLogStartupInfo(false);
    application.setWebEnvironment(true);
    application.addListeners(new ApplicationPidListener(PID_FILE));
    application.run(args);

    LOG.info("Prototype launched [OK]");
  }
}
