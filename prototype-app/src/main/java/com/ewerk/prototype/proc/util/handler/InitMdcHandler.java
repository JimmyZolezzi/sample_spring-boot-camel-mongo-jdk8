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

package com.ewerk.prototype.proc.util.handler;

import static com.google.common.base.Preconditions.checkArgument;

import org.apache.camel.Handler;
import org.jboss.logging.MDC;

/**
 * Initializes the {@link org.slf4j.MDC} context and adds the given key value pair to it.
 *
 * @author h.stolzenberg
 * @since 0.0.4
 */
public class InitMdcHandler {
  private final String label;
  private final String value;

  public InitMdcHandler(final String label, final String value) {
    checkArgument(label != null && !label.isEmpty(), "Argument 'label' must not be null or empty.");
    this.label = label;
    this.value = value;
  }

  @Handler
  public void handle() {
    MDC.put(label, value);
  }
}
