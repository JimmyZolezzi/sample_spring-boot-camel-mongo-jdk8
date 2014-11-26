/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ewerk.prototype.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.Nonnull;

/**
 * Global error handler for all WebMVC controllers. It is important that this class is public,
 * otherwise Spring won´t find the {@link ExceptionHandler} method.
 *
 * @author holgerstolzenberg
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
  public ResponseEntity<String> handleException(@Nonnull Exception exception) {
    LOG.error("Internal server error", exception);
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
