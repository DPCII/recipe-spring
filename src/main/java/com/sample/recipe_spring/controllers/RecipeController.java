package com.sample.recipe_spring.controllers;

import com.sample.recipe_spring.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/recipe/show/{id}"})
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeById(Long.valueOf(id)));

        return "recipe/show";
    }
}
