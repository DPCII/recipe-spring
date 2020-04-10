package com.sample.recipe_spring.services;

import com.sample.recipe_spring.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand showIngredient(Long recipeId, Long ingredientId);
}
