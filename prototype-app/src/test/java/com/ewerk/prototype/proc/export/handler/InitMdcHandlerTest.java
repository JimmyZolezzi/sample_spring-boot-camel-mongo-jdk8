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

package com.ewerk.prototype.proc.export.handler;

import static org.assertj.core.api.Assertions.assertThat;

import com.ewerk.prototype.proc.util.handler.InitMdcHandler;
import org.apache.log4j.MDC;
import org.testng.annotations.Test;

public class InitMdcHandlerTest {

  public static final String LABEL = "label";
  public static final String VALUE = "thisIsAValue";

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testHandleNullLabel() {
    new InitMdcHandler(null, null);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testHandleEmptyLabel() {
    new InitMdcHandler("", null);
  }

  @Test
  public void testHandle() {
    final InitMdcHandler handler = new InitMdcHandler(LABEL, VALUE);
    handler.handle();
    assertThat(MDC.get(LABEL)).isEqualTo(VALUE);
  }
}