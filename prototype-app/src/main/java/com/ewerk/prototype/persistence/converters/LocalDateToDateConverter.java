package com.ewerk.prototype.persistence.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class LocalDateToDateConverter implements Converter<LocalDate, Date> {
  public static final String TO_STRING =
    "{" + LocalDate.class.getSimpleName() + " -> " + Date.class.getSimpleName() + "}";

  @Override
  public Date convert(LocalDate localDate) {
    return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
  }

  @Override
  public String toString() {
    return TO_STRING;
  }
}