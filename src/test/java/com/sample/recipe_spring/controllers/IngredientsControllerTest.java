package com.sample.recipe_spring.controllers;

import com.sample.recipe_spring.commands.RecipeCommand;
import com.sample.recipe_spring.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IngredientsControllerTest {

    @Mock
    RecipeService recipeService;

    IngredientsController ingredientsController;

    MockMvc mockMvc;

    RecipeCommand recipeCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientsController = new IngredientsController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientsController).build();

        recipeCommand = new RecipeCommand();
        recipeCommand.setId(5L);
    }

    @Test
    void listIngredients() throws Exception {
        mockMvc.perform(get("/recipe/" + recipeCommand.getId() + "/ingredients"))
                .andExpect(view().name("recipe/ingredients/list"));
    }
}