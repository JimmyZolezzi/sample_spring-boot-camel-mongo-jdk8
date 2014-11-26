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

import static com.google.common.base.Preconditions.checkArgument;

import java.util.UUID;

/**
 * Utility class with helper functions for Camel routes.
 *
 * @author holgerstolzenberg
 * @since 0.0.4
 */
public final class Routes {

  public static final String MDC_ROUTE_ID = "routeId";
  public static final String MDC_UID = "uid";

  public static final String PREFIX = "prototype";

  private Routes() {
  }

  public static String id(final Class<?> routeBuilderClass, final String label) {
    checkArgument(routeBuilderClass != null,
      "Argument 'routeBuilderClass' must not be null or empty.");
    checkArgument(label != null && !label.isEmpty(), "Argument 'label' must not be null or empty.");

    //noinspection ConstantConditions
    return String.format("[%s/%s/%s]", PREFIX, routeBuilderClass.getCanonicalName(), label);
  }

  public static String processId() {
    return UUID.randomUUID().toString();
  }
}
