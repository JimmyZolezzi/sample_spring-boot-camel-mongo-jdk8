package com.ewerk.prototype.export;

import com.ewerk.prototype.export.handler.ClearMdcHandler;
import com.ewerk.prototype.export.handler.ExportHandler;
import com.ewerk.prototype.export.handler.InitMdcHandler;
import com.ewerk.prototype.export.util.UriBuilder;
import org.apache.camel.LoggingLevel;
import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Camel route builder the creates the main export route. Currently it just sets up quartz, the
 * route ID and some properties and then calls the main export handler.
 *
 * @author h.stolzenberg
 * @see org.apache.camel.builder.RouteBuilder
 * @see org.apache.camel.spring.SpringRouteBuilder
 * @since 0.0.4
 */
@Component
public class ExportRouteBuilder extends SpringRouteBuilder {
  private static final Logger LOG = LoggerFactory.getLogger(ExportRouteBuilder.class);

  private static final String MDC_ROUTE_ID_KEY = "routeId";

  private static final String ROUTE_LABEL = "export";

  private final String exportCronExp;
  private final boolean schedulerAutoStart;

  @Autowired
  public ExportRouteBuilder(@Value("${scheduler.export-cron-exp}") final String exportCronExp,
    @Value("${scheduler.auto-start}") final boolean schedulerAutoStart) {
    this.exportCronExp = exportCronExp;
    this.schedulerAutoStart = schedulerAutoStart;
  }

  @Override
  public void configure() throws Exception {
    LOG.info("Configure {} route", ROUTE_LABEL);

    final String uid = UUID.randomUUID().toString();

    //@formatter:off
    from(UriBuilder.quartz(ROUTE_LABEL, exportCronExp))
      .autoStartup(schedulerAutoStart)
      .routeId(routeId())
        .bean(new InitMdcHandler(MDC_ROUTE_ID_KEY, ROUTE_LABEL))
        .log(LoggingLevel.DEBUG, getClass().getCanonicalName(), String.format("Triggered: %s", uid))
        .bean(lookup(ExportHandler.class))
        .log(LoggingLevel.DEBUG, getClass().getCanonicalName(), String.format("Finished: %s", uid))
        .bean(new ClearMdcHandler());
      //@formatter:on
  }

  private String routeId() {
    return String.format("[prototype/%s/%s]", ExportRouteBuilder.class.getCanonicalName(),
      ROUTE_LABEL);
  }
}
