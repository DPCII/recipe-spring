package com.sample.recipe_spring.services;

import com.sample.recipe_spring.models.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe getRecipeById(Long aLong);
}
