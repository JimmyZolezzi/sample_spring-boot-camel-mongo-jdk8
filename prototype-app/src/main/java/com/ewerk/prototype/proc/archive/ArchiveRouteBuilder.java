package com.ewerk.prototype.proc.archive;

import com.ewerk.prototype.proc.archive.handler.ArchiveHandler;
import com.ewerk.prototype.proc.export.ExportRouteBuilder;
import com.ewerk.prototype.proc.export.handler.ClearMdcHandler;
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
 * Camel route builder the creates the main archive route. Currently it just sets up quartz, the
 * route ID and some properties and then calls the main archive handler.
 *
 * @author h.stolzenberg
 * @see org.apache.camel.builder.RouteBuilder
 * @see org.apache.camel.spring.SpringRouteBuilder
 * @since 0.0.4
 */
@Component
public class ArchiveRouteBuilder extends SpringRouteBuilder {
  private static final Logger LOG = LoggerFactory.getLogger(ArchiveRouteBuilder.class);

  private static final String ROUTE_LABEL = "archive";

  private final String cronExpArchive;
  private final boolean schedulerAutoStart;

  @Autowired
  public ArchiveRouteBuilder(@Value("${scheduler.cron-exp-archive}") final String cronExpArchive,
    @Value("${scheduler.auto-start}") final boolean schedulerAutoStart) {
    this.cronExpArchive = cronExpArchive;
    this.schedulerAutoStart = schedulerAutoStart;
  }

  @Override
  public void configure() throws Exception {
    LOG.info("Configure {} route", ROUTE_LABEL);

    final String uid = UUID.randomUUID().toString();

    //@formatter:off
    from(UriBuilder.quartz(ROUTE_LABEL, cronExpArchive))
      .autoStartup(schedulerAutoStart)
      .routeId(Routes.id(ArchiveRouteBuilder.class,ROUTE_LABEL))
        .bean(new InitMdcHandler(Routes.MDC_ROUTE_ID, ROUTE_LABEL))
        .bean(new InitMdcHandler(Routes.MDC_UID, uid))
        .log(LoggingLevel.DEBUG, ExportRouteBuilder.class.getCanonicalName(), "Archiving ...")
        .bean(lookup(ArchiveHandler.class))
        .log(LoggingLevel.DEBUG, ExportRouteBuilder.class.getCanonicalName(), "Archiving finished")
        .bean(new ClearMdcHandler());
      //@formatter:on
  }
}
