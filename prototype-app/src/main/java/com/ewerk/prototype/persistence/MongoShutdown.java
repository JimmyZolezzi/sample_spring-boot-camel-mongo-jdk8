package com.ewerk.prototype.persistence;

import com.mongodb.Mongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * Helper class to shut down the connections to the mongo db on application halt.
 *
 * @author h.stolzenberg
 * @since 0.0.2
 */
@Component
public final class MongoShutdown {

  private static final Logger LOG = LoggerFactory.getLogger(LoggerFactory.class);

  private final Mongo mongo;

  @Autowired
  public MongoShutdown(@SuppressWarnings("SpringJavaAutowiringInspection") final Mongo mongo) {
    this.mongo = mongo;
  }

  @PreDestroy
  public void shutdown() {
    LOG.info("Shutting down MongoDB connections");
    mongo.close();
  }
}
