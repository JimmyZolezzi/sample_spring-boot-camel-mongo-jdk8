package com.ewerk.prototype.api;

import com.ewerk.prototype.AbstractIntegTest;
import com.jayway.restassured.RestAssured;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.BeforeClass;

/**
 * This is the base class for all integration tests that work with the REST API. Some accessor
 * methods provide easy access to the embedded servlet containers setup. Please note that we create
 * the embedded container with a random port number, which then is retrieved from the spring
 * context.
 *
 * @author h.stolzenberg
 * @since 0.0.3
 */
abstract class AbstractApiIntegTest extends AbstractIntegTest {

  @Value("${server.context-path}")
  private String contextPath;

  @Value("${local.server.port}")
  private int port;

  @BeforeClass
  public void setupRestAssured() {
    RestAssured.port = port();
  }

  protected final int port() {
    return port;
  }

  protected final String contextPath() {
    return contextPath;
  }

  protected final String url(String path) {
    String tmp = path != null ? path : "";
    if (!tmp.startsWith("/")) {
      tmp = "/" + tmp;
    }
    final String url = contextPath() + tmp;
    LOG.debug("Url: {}", url);
    return url;
  }
}
