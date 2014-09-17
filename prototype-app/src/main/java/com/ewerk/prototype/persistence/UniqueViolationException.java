package com.ewerk.prototype.persistence;

/**
 * Exception class used for specific exception that may occur during runtime accessing the
 * persistence layer.
 *
 * @author h.stolzenberg
 * @since 0.0.3
 */
public final class UniqueViolationException extends RuntimeException {
  public UniqueViolationException(final String message, Object... args) {
    super(String.format(message, args));
  }
}