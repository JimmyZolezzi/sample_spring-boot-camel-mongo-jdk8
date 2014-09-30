/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ewerk.prototype.proc.archiving;

import static com.ewerk.prototype.proc.util.Routes.MDC_ROUTE_ID;
import static com.ewerk.prototype.proc.util.Routes.MDC_UID;
import static com.ewerk.prototype.proc.util.Routes.id;
import static com.ewerk.prototype.proc.util.Routes.processId;

import com.ewerk.prototype.proc.archiving.handler.ArchiveHandler;
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
 * Camel route builder the creates the main archiving route. Currently it just sets up quartz, the
 * route ID and some properties and then calls the main archiving handler.
 *
 * @author h.stolzenberg
 * @see org.apache.camel.builder.RouteBuilder
 * @see org.apache.camel.spring.SpringRouteBuilder
 * @since 0.0.4
 */
@Component
public class ArchiveRouteBuilder extends AbstractQuartzRouteBuilder {
  private static final Logger LOG = LoggerFactory.getLogger(ArchiveRouteBuilder.class);

  private static final String ROUTE_LABEL = "archiving";

  @Autowired
  public ArchiveRouteBuilder(@Value("${scheduler.cron-exp-archiving}") final String cronExpArchive,
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
