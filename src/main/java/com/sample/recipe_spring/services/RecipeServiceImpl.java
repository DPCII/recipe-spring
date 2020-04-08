package com.sample.recipe_spring.services;

import com.sample.recipe_spring.models.Recipe;
import com.sample.recipe_spring.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("This is in RecipeServiceImpl");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe getRecipeById(Long aLong) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(aLong);

        if(recipeOptional.isEmpty())
            throw new RuntimeException();

        return recipeOptional.get();
    }
}
