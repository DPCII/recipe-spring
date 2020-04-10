package com.sample.recipe_spring.services;

import com.sample.recipe_spring.commands.IngredientCommand;
import com.sample.recipe_spring.converters.IngredientCommandToIngredient;
import com.sample.recipe_spring.converters.IngredientToIngredientCommand;
import com.sample.recipe_spring.models.Recipe;
import com.sample.recipe_spring.repositories.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@AllArgsConstructor
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    @Override
    public IngredientCommand showIngredient(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(recipeOptional.isEmpty())
            log.error("Recipe Id not found: " + recipeId);

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientToIngredientCommand::convert).findFirst();

        if(ingredientCommandOptional.isEmpty())
            log.error("ingredient id not found: " + ingredientId);

        return ingredientCommandOptional.get();
    }
}
