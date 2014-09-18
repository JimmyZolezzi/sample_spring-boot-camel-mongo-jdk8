package com.ewerk.prototype.proc.util;

import org.apache.camel.spring.SpringRouteBuilder;

/**
 * Abstract base class for all quartz based Camel {@link org.apache.camel.spring.SpringRouteBuilder}
 * classes used for the business processes. Provides some accessor methods that are useful to be
 * used within the routes.
 *
 * @author h.stolzenberg
 * @since 0.0.4
 */
public abstract class AbstractQuartzRouteBuilder extends SpringRouteBuilder {

  private final String cronExp;
  private final boolean schedulerAutoStart;

  public AbstractQuartzRouteBuilder(final String cronExp, final boolean schedulerAutoStart) {
    this.cronExp = cronExp;
    this.schedulerAutoStart = schedulerAutoStart;
  }

  protected final String cronExp() {
    return cronExp;
  }

  protected final boolean schedulerAutoStart() {
    return schedulerAutoStart;
  }

  protected final String routeLogger() {
    return getClass().getCanonicalName();
  }
}
