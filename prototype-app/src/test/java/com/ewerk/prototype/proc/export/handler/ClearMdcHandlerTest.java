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

import com.ewerk.prototype.AbstractUnitTest;
import com.ewerk.prototype.proc.util.handler.ClearMdcHandler;
import org.slf4j.MDC;
import org.testng.annotations.Test;

public class ClearMdcHandlerTest extends AbstractUnitTest {

  @Test
  public void testHandle() {
    final ClearMdcHandler handler = new ClearMdcHandler();
    handler.handle();
    assertThat(MDC.get("test")).isNull();
  }
}