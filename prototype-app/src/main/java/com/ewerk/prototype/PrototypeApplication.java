package com.ewerk.prototype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * This is the application entry point. Utilizing the main method makes it easy to run/debug the
 * application form CLI oder IDE. In the Spring samples this class is often annotated with {@link
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
@ComponentScan
@EnableAutoConfiguration
public final class PrototypeApplication {

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
