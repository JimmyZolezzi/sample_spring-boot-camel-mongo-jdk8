package com.ewerk.prototype.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * This bean factory enables the Spring WebMVC features of spring-boot. Currently no customizations
 * are made.
 *
 * @author h.stolzenberg
 * @since 0.0.4
 */
@EnableWebMvc
@Configuration
public class ApiConfiguration extends WebMvcConfigurerAdapter {

  @SuppressWarnings("SpringJavaAutowiringInspection")
  @Autowired
  private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(mappingJackson2HttpMessageConverter);
  }
}
