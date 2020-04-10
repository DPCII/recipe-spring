package com.sample.recipe_spring.controllers;

import com.sample.recipe_spring.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientsController {

    private final RecipeService recipeService;

    public IngredientsController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable String id, Model model) {
        log.debug("Getting ingredient list for recipe id: " + id);

        model.addAttribute("recipe", recipeService.findCommandRecipeById(Long.valueOf(id)));

        return "recipe/ingredients/list";
    }

}
