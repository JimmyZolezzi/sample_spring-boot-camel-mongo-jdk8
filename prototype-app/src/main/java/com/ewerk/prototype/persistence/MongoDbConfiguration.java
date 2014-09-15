package com.ewerk.prototype.persistence;

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
  // no beans here so far
}
