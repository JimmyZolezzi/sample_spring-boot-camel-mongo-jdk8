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

package com.ewerk.prototype.persistence;

import com.ewerk.prototype.persistence.converters.DateToLocalDateConverter;
import com.ewerk.prototype.persistence.converters.LocalDateToDateConverter;
import com.google.common.collect.Lists;
import com.mongodb.MongoClientOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;

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
public class PersistenceConfiguration {

  private static final Logger LOG = LoggerFactory.getLogger(LoggerFactory.class);

  @Bean
  public CustomConversions customConversions() {
    final ArrayList<? extends Converter<? extends Serializable, ? extends Serializable>>
      converters =
      Lists.newArrayList(new DateToLocalDateConverter(), new LocalDateToDateConverter());
    LOG.info("Register converters: " + converters);
    return new CustomConversions(converters);
  }

  @Bean
  public MappingMongoConverter mappingMongoConverter(MongoDbFactory mongoDbFactory,
    MongoMappingContext mongoMappingContext, CustomConversions customConversions) throws Exception {

    MappingMongoConverter converter =
      new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), mongoMappingContext);
    converter.setCustomConversions(customConversions);

    return converter;
  }

  @Bean
  public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory,
    MappingMongoConverter mappingMongoConverter) throws UnknownHostException {
    return new MongoTemplate(mongoDbFactory, mappingMongoConverter);
  }

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
    @Value("${spring.data.mongodb.min-connections:2}") final int minConnections,
    @Value("${spring.data.mongodb.max-connections:10}") final int maxConnections,
    @Value("${spring.data.mongodb.socket-connect.ms:5000}") final int connectTimeout,
    @Value("${spring.data.mongodb.socket-timeout.ms:5000}") final int socketTimeout) {

    return MongoClientOptions.builder()
      .legacyDefaults()
      .minConnectionsPerHost(minConnections)
      .connectionsPerHost(maxConnections)
      .connectTimeout(connectTimeout)
      .socketTimeout(socketTimeout)
      .build();
  }
}
