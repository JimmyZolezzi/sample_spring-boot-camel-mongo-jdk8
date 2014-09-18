package com.ewerk.prototype.proc.export.handler;

import static org.assertj.core.api.Assertions.assertThat;

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