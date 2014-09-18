package com.ewerk.prototype.proc;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.camel.builder.RouteBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author h.stolzenberg
 * @since 0.0.4
 */
public class ProcessConfigurationIntegTest {

  @Autowired
  private RouteBuilder exportRouteBuilder;

  @Autowired
  private RouteBuilder archiveRouteBuilder;

  @Test
  public void testExportRouteBuilderPresent() {
    assertThat(exportRouteBuilder).isNotNull();
  }

  @Test
  public void testArchiveRouteBuilderPresent() {
    assertThat(archiveRouteBuilder).isNotNull();
  }
}
