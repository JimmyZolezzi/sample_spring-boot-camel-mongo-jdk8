package com.ewerk.prototype.persistence;

import com.mongodb.MongoClientOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * This configuration class is used to setup the connection to the MongoDB instance. Here we should
 * provide all the beans necessary for the persistence layer or for customizing the mongo setup.
 *
 * @author h.stolzenberg
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.data.mongodb.repository.config.EnableMongoRepositories
 * @see org.springframework.transaction.annotation.EnableTransactionManagement
 * @since 0.0.2
 */
@Configuration
@EnableMongoRepositories
@EnableTransactionManagement
public class MongoDbConfiguration {

  /**
   * Customize the MongoDB client connection properties.
   *
   * @return A {@link MongoClientOptions} bean that is used by the {@link
   * org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration} when creating the {@link
   * com.mongodb.Mongo} facade
   * @see com.mongodb.MongoClientOptions
   * @see org.springframework.core.env.Environment
   */
  @Bean
  public MongoClientOptions mongoClientOptions(
    @Value("${spring.data.mongodb.min.connections:2}") final int minConnections,
    @Value("${spring.data.mongodb.max.connections:10}") final int maxConnections,
    @Value("${spring.data.mongodb.socket.connect.ms:5000}") final int connectTimeout,
    @Value("${spring.data.mongodb.socket.timeout.ms:5000}") final int socketTimeout) {

    return MongoClientOptions.builder()
      .legacyDefaults()
      .minConnectionsPerHost(minConnections)
      .connectionsPerHost(maxConnections)
      .connectTimeout(connectTimeout)
      .socketTimeout(socketTimeout)
      .build();
  }
}
