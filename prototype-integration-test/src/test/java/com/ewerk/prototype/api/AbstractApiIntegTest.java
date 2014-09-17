package com.ewerk.prototype.api;

import com.ewerk.prototype.AbstractIntegTest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.web.client.RestTemplate;
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

  // TODO h.stolzenberg: Use RestAssured framework
  private RestTemplate testRestTemplate = new TestRestTemplate();

  @BeforeClass
  public void setupRestAssured() {
    // TODO h.stolzenberg: set port for RestAssured
  }

  protected final int port() {
    return port;
  }

  protected final String contextPath() {
    return contextPath;
  }

  protected final String baseUrl() {
    return String.format("http://localhost:%d%s", port(), contextPath());
  }

  protected final String url(String path) {
    String tmp = path != null ? path : "";
    if (!tmp.startsWith("/")) {
      tmp = "/" + tmp;
    }
    final String url = baseUrl() + tmp;
    LOG.debug("Url: {}", url);
    return url;
  }

  public RestTemplate testRestTemplate() {
    return testRestTemplate;
  }
}
