package com.ewerk.prototype.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import com.ewerk.prototype.AbstractIntegTest;
import com.mongodb.MongoClientOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * @author h.stolzenberg
 * @since 0.0.3
 */
public class PersistenceConfigurationIntegTest extends AbstractIntegTest {
  @Autowired
  private MongoClientOptions options;

  @Test
  public void testMongoClientOptions() {
    assertThat(options).isNotNull();
  }
}
