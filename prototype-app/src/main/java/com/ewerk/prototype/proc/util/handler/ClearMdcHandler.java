package com.ewerk.prototype.proc.util.handler;

import org.apache.camel.Handler;
import org.slf4j.MDC;

/**
 * Clears the {@link org.slf4j.MDC} context.
 *
 * @author h.stolzenberg
 * @since 0.0.4
 */
public class ClearMdcHandler {
	@Handler
	public void handle() {
		MDC.clear();
	}
}
