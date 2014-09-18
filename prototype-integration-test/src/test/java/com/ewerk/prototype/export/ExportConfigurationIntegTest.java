package com.ewerk.prototype.export;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author h.stolzenberg
 * @since 0.0.4
 */
public class ExportConfigurationIntegTest {
  @Autowired
  private ExportRouteBuilder exportRouteBuilder;

  @Test
  public void testExportRouteBuilderPresent() {
    assertThat(exportRouteBuilder).isNotNull();
  }
}
