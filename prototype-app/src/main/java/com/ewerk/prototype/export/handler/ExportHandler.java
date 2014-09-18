package com.ewerk.prototype.export.handler;

import com.ewerk.prototype.export.util.UsedByCamel;
import org.apache.camel.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This camel handler is the actual workhorse of the export camel route. It takes the given {@link
 * com.ewerk.prototype.model.Person} and writes its JSON representation to the file system.
 *
 * @author h.stolzenberg
 * @since 0.0.4
 */
@Component
public class ExportHandler {
  private static final Logger LOG = LoggerFactory.getLogger(ExportHandler.class);

  @UsedByCamel
  @Handler
  public void export() {
    // TODO h.stolzenberg: do the real export stuff
    LOG.info("Exporting Mongo data ...");
  }
}
