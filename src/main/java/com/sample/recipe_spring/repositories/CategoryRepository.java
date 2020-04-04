package com.sample.recipe_spring.repositories;

import com.sample.recipe_spring.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Set<Category> findByDescription(String description);
}
