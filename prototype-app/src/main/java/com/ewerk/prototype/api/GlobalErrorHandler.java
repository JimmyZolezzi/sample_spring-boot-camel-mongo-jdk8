package com.ewerk.prototype.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global error handler for all WebMVC controllers. It is important that this class is public,
 * otherwise Spring won´t find the {@link ExceptionHandler} method.
 *
 * @author h.stolzenberg
 * @since 0.0.3
 */
@ControllerAdvice
public class GlobalErrorHandler {
  private static final Logger LOG = LoggerFactory.getLogger(GlobalErrorHandler.class);

  /**
   * Called upon internal error. Cannot be called asynchronously.
   *
   * @param exception The exception that was thrown internally.
   * @return A {@link org.springframework.http.ResponseEntity} with status 500
   * (INTERNAL_SERVER_ERROR) and the exception message
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception exception) {
    LOG.error("Internal server error", exception);
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
