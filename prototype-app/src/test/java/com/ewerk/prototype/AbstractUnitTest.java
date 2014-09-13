package com.ewerk.prototype;

import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;

public class AbstractUnitTest {
  protected static final Logger LOG = LoggerFactory.getLogger(AbstractUnitTest.class);

  @BeforeClass
  public void initMockito() {
    MockitoAnnotations.initMocks(this);
  }
}
