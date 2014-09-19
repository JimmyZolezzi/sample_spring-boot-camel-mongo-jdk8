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