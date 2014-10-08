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
    if (source == null) {
      return null;
    }
    return source.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }

  @Override
  public String toString() {
    return TO_STRING;
  }
}