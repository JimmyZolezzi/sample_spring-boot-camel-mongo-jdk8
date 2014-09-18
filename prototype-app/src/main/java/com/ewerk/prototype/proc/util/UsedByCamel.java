package com.ewerk.prototype.proc.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This is just a helper annotation used to mark methods that are used by Camel, e.g. {@link
 * org.apache.camel.Handler} methods. The annotation makes it easy to configure the IDE to not mark
 * them as unused.
 *
 * @author h.stolzenberg
 * @since 0.0.4
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface UsedByCamel {
}
