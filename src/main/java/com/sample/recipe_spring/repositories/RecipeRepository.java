package com.sample.recipe_spring.repositories;

import com.sample.recipe_spring.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Set<Recipe> findAll();
}
