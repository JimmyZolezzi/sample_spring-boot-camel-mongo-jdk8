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
