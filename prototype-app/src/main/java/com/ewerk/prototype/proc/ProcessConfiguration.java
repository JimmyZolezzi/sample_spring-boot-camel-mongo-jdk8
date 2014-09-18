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
 * @author h.stolzenberg
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
