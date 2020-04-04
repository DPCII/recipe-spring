package com.sample.recipe_spring.services;

import com.sample.recipe_spring.models.Recipe;
import com.sample.recipe_spring.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }
}
