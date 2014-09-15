package com.ewerk.prototype.persistence;

import static org.mockito.Mockito.mock;

import com.ewerk.prototype.AbstractUnitTest;
import com.mongodb.Mongo;
import org.testng.annotations.Test;

public class MongoShutdownTest extends AbstractUnitTest {

  @Test
  public void testShutdown() {
    MongoShutdown mongoShutdown = new MongoShutdown(mock(Mongo.class));
    mongoShutdown.shutdown();
  }
}