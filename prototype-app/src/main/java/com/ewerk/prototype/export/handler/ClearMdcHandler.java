package com.ewerk.prototype.export.handler;

import com.ewerk.prototype.export.util.UsedByCamel;
import org.apache.camel.Handler;
import org.slf4j.MDC;

/**
 * Clears the {@link org.slf4j.MDC} context.
 *
 * @author h.stolzenberg
 * @since 0.0.4
 */
public class ClearMdcHandler {

  @UsedByCamel
  @Handler
  public void handle() {
    MDC.clear();
  }
}
