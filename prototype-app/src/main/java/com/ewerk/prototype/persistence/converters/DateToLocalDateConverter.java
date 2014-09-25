package com.ewerk.prototype.persistence.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateToLocalDateConverter implements Converter<Date, LocalDate> {
  public static final String TO_STRING =
    "{" + Date.class.getSimpleName() + " -> " + LocalDate.class.getSimpleName() + "}";

  @Override
  public LocalDate convert(Date source) {
    return source.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }

  @Override
  public String toString() {
    return TO_STRING;
  }
}