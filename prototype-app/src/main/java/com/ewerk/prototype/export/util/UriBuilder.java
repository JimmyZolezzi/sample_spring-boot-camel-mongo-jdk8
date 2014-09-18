package com.ewerk.prototype.export.util;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Utility class for creating various types of URIs.
 *
 * @author h.stolzenberg
 * @since 0.0.4
 */
public final class UriBuilder {

  private UriBuilder() {
  }

  public static String quartz(final String name, final String cronExp) {
    checkArgument(name != null && !name.isEmpty(),
      "The argument 'name' must not be null or empty.");
    checkArgument(cronExp != null && !cronExp.isEmpty(),
      "The argument 'cronExp' must not be null or empty.");

    return "quartz2://rts/" + name + "?cron=" + cronExp.replaceAll(" ", "+") + "&stateful=true";
  }
}
