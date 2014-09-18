package com.ewerk.prototype.proc.export;

import com.ewerk.prototype.proc.export.handler.ClearMdcHandler;
import com.ewerk.prototype.proc.export.handler.ExportHandler;
import com.ewerk.prototype.proc.export.handler.InitMdcHandler;
import com.ewerk.prototype.proc.util.Routes;
import com.ewerk.prototype.proc.util.UriBuilder;
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

  private static final String MDC_ROUTE_ID = "routeId";
  private static final String MDC_UID = "uid";

  private static final String ROUTE_LABEL = "export";

  private final String exportCronExp;
  private final boolean schedulerAutoStart;

  @Autowired
  public ExportRouteBuilder(@Value("${scheduler.cron-exp-export}") final String exportCronExp,
    @Value("${scheduler.auto-start}") final boolean schedulerAutoStart) {
    this.exportCronExp = exportCronExp;
    this.schedulerAutoStart = schedulerAutoStart;
  }

  @Override
  public void configure() throws Exception {
    LOG.info("Configure {} route", ROUTE_LABEL);

    final String routeId = Routes.id(ExportRouteBuilder.class, ROUTE_LABEL);
    final String processId = UUID.randomUUID().toString();

    //@formatter:off
    from(UriBuilder.quartz(ROUTE_LABEL, exportCronExp))
      .autoStartup(schedulerAutoStart)
      .routeId(routeId)
        .bean(new InitMdcHandler(MDC_ROUTE_ID, ROUTE_LABEL))
        .bean(new InitMdcHandler(MDC_UID, processId))
        .log(LoggingLevel.DEBUG, ExportRouteBuilder.class.getCanonicalName(), "Exporting ...")
        .bean(lookup(ExportHandler.class))
        .log(LoggingLevel.DEBUG, ExportRouteBuilder.class.getCanonicalName(), "Export finished")
        .bean(new ClearMdcHandler());
    //@formatter:on
  }
}
