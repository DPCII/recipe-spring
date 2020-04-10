package com.sample.recipe_spring.services;

import com.sample.recipe_spring.commands.RecipeCommand;
import com.sample.recipe_spring.converters.RecipeCommandToRecipe;
import com.sample.recipe_spring.converters.RecipeToRecipeCommand;
import com.sample.recipe_spring.models.Recipe;
import com.sample.recipe_spring.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
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

    @Transactional
    @Override
    public RecipeCommand findCommandRecipeById(Long aLong) {
        return recipeToRecipeCommand.convert(getRecipeById(aLong));
    }

    @Transactional
    @Override
    public RecipeCommand savedRecipe(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:: " + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public void deleteById(Long aLong) {
        recipeRepository.deleteById(aLong);
    }


}
