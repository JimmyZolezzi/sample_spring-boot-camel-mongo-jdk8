package com.ewerk.prototype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * This is the application entry point. Utilizing the main method makes it easy to run/debug the
 * application form CLI oder IDE. In the Spring samples this class is often annotated with {@link
 * org.springframework.context.annotation.Configuration} and is therefore used as bean factory. We
 * do not go this way in this example, as we want to use more purpose specific configuration
 * classes.
 *
 * @author h.stolzenberg
 * @since 0.0.1
 */
@ComponentScan
@EnableAutoConfiguration
public class Application extends SpringApplication {

  private static final Logger LOG = LoggerFactory.getLogger(LoggerFactory.class);

  public static void main(String[] args) {
    SpringApplication application = new SpringApplication(Application.class);
    application.setHeadless(true);
    application.setRegisterShutdownHook(true);
    application.setLogStartupInfo(true);
    application.setWebEnvironment(true);
    application.run(args);

    LOG.info("Prototype launched [OK]");
  }
}
