package com.ewerk.prototype.proc.archive.handler;

import com.ewerk.prototype.proc.util.UsedByCamel;
import org.apache.camel.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * TODO h.stolzenberg: Document
 *
 * @author h.stolzenberg
 * @since 0.0.4
 */
@Component
public class ArchiveHandler {
  private static final Logger LOG = LoggerFactory.getLogger(ArchiveHandler.class);

  @UsedByCamel
  @Handler
  public void archive() {
    LOG.info("Archiving ...");
  }
}
