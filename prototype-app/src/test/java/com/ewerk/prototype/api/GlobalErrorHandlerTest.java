package com.ewerk.prototype.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.ewerk.prototype.AbstractUnitTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

public class GlobalErrorHandlerTest extends AbstractUnitTest {

  @Test
  public void testHandleException() {
    GlobalErrorHandler handler = new GlobalErrorHandler();
    final ResponseEntity<String> responseEntity =
      handler.handleException(new RuntimeException("Test"));
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    assertThat(responseEntity.getBody()).isEqualTo("Test");
  }
}