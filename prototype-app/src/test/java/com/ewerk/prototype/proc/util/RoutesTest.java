package com.ewerk.prototype.proc.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.ewerk.prototype.AbstractUnitTest;
import org.testng.annotations.Test;

public class RoutesTest extends AbstractUnitTest {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testIdWithNullClassArg() {
    Routes.id(null, null);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testIdWithNullLabel() {
    Routes.id(RoutesTest.class, null);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testIdWithNullEmpty() {
    Routes.id(RoutesTest.class, "");
  }

  @Test
  public void testId() {
    final String id = Routes.id(RoutesTest.class, "unit-test");
    assertThat(id).isEqualTo("[prototype/com.ewerk.prototype.proc.util.RoutesTest/unit-test]");
  }
}