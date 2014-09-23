package com.ewerk.prototype.proc.archive;

import static com.ewerk.prototype.proc.util.Routes.MDC_ROUTE_ID;
import static com.ewerk.prototype.proc.util.Routes.MDC_UID;
import static com.ewerk.prototype.proc.util.Routes.id;
import static com.ewerk.prototype.proc.util.Routes.processId;

import com.ewerk.prototype.proc.archive.handler.ArchiveHandler;
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
 * Camel route builder the creates the main archive route. Currently it just sets up quartz, the
 * route ID and some properties and then calls the main archive handler.
 *
 * @author h.stolzenberg
 * @see org.apache.camel.builder.RouteBuilder
 * @see org.apache.camel.spring.SpringRouteBuilder
 * @since 0.0.4
 */
@Component
public class ArchiveRouteBuilder extends AbstractQuartzRouteBuilder {
  private static final Logger LOG = LoggerFactory.getLogger(ArchiveRouteBuilder.class);

  private static final String ROUTE_LABEL = "archive";

  @Autowired
  public ArchiveRouteBuilder(@Value("${scheduler.cron-exp-archive}") final String cronExpArchive,
    @Value("${scheduler.auto-start}") final boolean schedulerAutoStart) {
    super(cronExpArchive, schedulerAutoStart);
  }

  @Override
  public void configure() throws Exception {
    LOG.info("Configure {} route", ROUTE_LABEL);

    //@formatter:off
    from(UriBuilder.quartz(ROUTE_LABEL, cronExp()))
      .autoStartup(schedulerAutoStart())
      .routeId(id(ArchiveRouteBuilder.class,ROUTE_LABEL))
        .bean(new InitMdcHandler(MDC_ROUTE_ID, ROUTE_LABEL))
        .bean(new InitMdcHandler(MDC_UID, processId()))
        .log(LoggingLevel.DEBUG, routeLogger(), "Archiving ...")
        .bean(lookup(ArchiveHandler.class))
        .log(LoggingLevel.DEBUG, routeLogger(), "Archiving finished")
        .bean(new ClearMdcHandler());
      //@formatter:on
  }
}
