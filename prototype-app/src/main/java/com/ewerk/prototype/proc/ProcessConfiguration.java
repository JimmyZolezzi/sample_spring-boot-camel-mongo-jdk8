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

package com.ewerk.prototype.proc;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.quartz2.QuartzComponent;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Spring configuration class. Creates all beans that are involved in the export process.
 *
 * @author holgerstolzenberg
 * @see org.springframework.context.annotation.Configuration
 * @since 0.0.4
 */
@Configuration
public class ProcessConfiguration {
  @Autowired
  private RoutesBuilder exportRouteBuilder;

  @Autowired
  private RoutesBuilder archiveRouteBuilder;

  @Bean
  public SpringCamelContext camelContext(ApplicationContext applicationContext) throws Exception {
    SpringCamelContext camelContext = new SpringCamelContext(applicationContext);
    camelContext.addRoutes(exportRouteBuilder);
    camelContext.addRoutes(archiveRouteBuilder);
    return camelContext;
  }

  @Bean
  public QuartzComponent quartzComponent(
    @Value("${scheduler.startup-delay-seconds}") final int startupDelaySeconds) {
    Properties quartzProperties = new Properties();
    quartzProperties.put("org.quartz.plugin.shutdownhook.class",
      "org.quartz.plugins.management.ShutdownHookPlugin");
    quartzProperties.put("org.quartz.plugin.shutdownhook.cleanShutdown", "true");

    QuartzComponent component = new QuartzComponent();
    component.setEnableJmx(false);
    component.setStartDelayedSeconds(startupDelaySeconds);
    component.setProperties(quartzProperties);
    return component;
  }
}
