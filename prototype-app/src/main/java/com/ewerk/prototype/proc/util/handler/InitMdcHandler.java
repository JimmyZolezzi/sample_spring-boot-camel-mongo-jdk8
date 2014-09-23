package com.ewerk.prototype.proc.util.handler;

import static com.google.common.base.Preconditions.checkArgument;

import org.apache.camel.Handler;
import org.jboss.logging.MDC;

/**
 * Initializes the {@link org.slf4j.MDC} context and adds the given key value pair to it.
 *
 * @author h.stolzenberg
 * @since 0.0.4
 */
public class InitMdcHandler {
	private final String label;
	private final String value;

	public InitMdcHandler(final String label, final String value) {
		checkArgument(label != null && !label.isEmpty(),
				"Argument 'label' must not be null or empty.");
		this.label = label;
		this.value = value;
	}

	@Handler
	public void handle() {
		MDC.put(label, value);
	}
}
