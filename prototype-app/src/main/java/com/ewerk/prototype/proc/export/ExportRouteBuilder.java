package com.ewerk.prototype.proc.export;

import static com.ewerk.prototype.proc.util.Routes.MDC_ROUTE_ID;
import static com.ewerk.prototype.proc.util.Routes.MDC_UID;
import static com.ewerk.prototype.proc.util.Routes.id;
import static com.ewerk.prototype.proc.util.Routes.processId;

import com.ewerk.prototype.proc.export.handler.ExportHandler;
import com.ewerk.prototype.proc.util.AbstractQuartzRouteBuilder;
import com.ewerk.prototype.proc.util.UriBuilder;
import com.ewerk.prototype.proc.util.handler.ClearMdcHandler;
import com.ewerk.prototype.proc.util.handler.InitMdcHandler;
import org.apache.camel.LoggingLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
public class ExportRouteBuilder extends AbstractQuartzRouteBuilder {
  private static final Logger LOG = LoggerFactory.getLogger(ExportRouteBuilder.class);

  private static final String ROUTE_LABEL = "export";

  @Autowired
  public ExportRouteBuilder(@Value("${scheduler.cron-exp-export}") final String cronExpExport,
    @Value("${scheduler.auto-start}") final boolean schedulerAutoStart) {
    super(cronExpExport, schedulerAutoStart);
  }

  @Override
  public void configure() throws Exception {
    LOG.info("Configure {} route", ROUTE_LABEL);

    final String routeId = id(ExportRouteBuilder.class, ROUTE_LABEL);

    //@formatter:off
    from(UriBuilder.quartz(ROUTE_LABEL, cronExp()))
      .autoStartup(schedulerAutoStart())
      .routeId(routeId)
        .bean(new InitMdcHandler(MDC_ROUTE_ID, ROUTE_LABEL))
        .bean(new InitMdcHandler(MDC_UID, processId()))
        .log(LoggingLevel.DEBUG, routeLogger(), "Exporting ...")
        .bean(lookup(ExportHandler.class))
        .log(LoggingLevel.DEBUG, routeLogger(), "Export finished")
        .bean(new ClearMdcHandler());
    //@formatter:on
  }
}
