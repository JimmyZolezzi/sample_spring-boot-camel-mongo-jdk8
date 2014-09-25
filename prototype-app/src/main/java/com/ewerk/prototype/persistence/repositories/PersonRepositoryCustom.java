package com.ewerk.prototype.persistence.repositories;

import com.ewerk.prototype.model.Person;

/**
 * Spring-data custom repository declaration for the {@link Person} entity.
 *
 * @author h.stolzenberg
 * @since 0.0.3
 */
public interface PersonRepositoryCustom {
  Person create(Person person);
}
