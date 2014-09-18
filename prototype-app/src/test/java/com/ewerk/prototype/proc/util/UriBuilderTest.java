package com.ewerk.prototype.proc.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.ewerk.prototype.AbstractUnitTest;
import org.testng.annotations.Test;

public class UriBuilderTest extends AbstractUnitTest {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testQuartzWithNullParam() {
    UriBuilder.quartz(null, null);
  }

  @Test
  public void testQuartz() {
    String label = "spt";
    String cronExp = "0/5 * * * * ?";

    String uri = UriBuilder.quartz(label, cronExp);
    assertThat(uri).isEqualTo("quartz2://rts/spt?cron=0/5+*+*+*+*+?&stateful=true");
  }
}