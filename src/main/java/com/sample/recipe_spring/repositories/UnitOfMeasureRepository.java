package com.sample.recipe_spring.repositories;

import com.sample.recipe_spring.models.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    UnitOfMeasure findByDescription(String description);
}
