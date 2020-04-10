package com.sample.recipe_spring.bootstrapData;

import com.sample.recipe_spring.models.*;
import com.sample.recipe_spring.repositories.CategoryRepository;
import com.sample.recipe_spring.repositories.RecipeRepository;
import com.sample.recipe_spring.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Component
public class BootStrapData implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public BootStrapData(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    public void loadData() throws Exception {

        List<Category> categoriesInitialLoad = new ArrayList<>((Collection<Category>) categoryRepository.findAll());

        Ingredient eggs = new Ingredient();
        eggs.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Each"));
        eggs.setDescription("Eggs");
        eggs.setAmount(BigDecimal.valueOf(12));

        Ingredient flour = new Ingredient();
        flour.setAmount(BigDecimal.valueOf(1));
        flour.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Pounds"));
        flour.setDescription("Flour");

        Ingredient milk = new Ingredient();
        milk.setDescription("Milk");
        milk.setAmount(BigDecimal.valueOf(3));
        milk.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Cups"));

        Ingredient crab = new Ingredient();
        crab.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Pound"));
        crab.setAmount(BigDecimal.valueOf(20));
        crab.setDescription("Crab");

        Ingredient avacado = Ingredient.builder()
                .unitOfMeasure(unitOfMeasureRepository.findByDescription("Pound"))
                .description("Avacado")
                .amount(BigDecimal.valueOf(3))
                .build();


        Set<Ingredient> recipeTestIngredients = new HashSet<>();
        recipeTestIngredients.add(avacado);
        Notes notes = new Notes();
        notes.setNotes("Lorem ipsum felo nostrum deus ipsum felo nostrum deus ipsum felo nostrum deus ipsum felo nostrum deus ipsum felo nostrum deus");
        Recipe recipeTest = Recipe
                .builder()
                .description("Test Recipe")
                .categories(categoryRepository.findByDescription("Breakfast"))
                .difficulty(Difficulty.HARD)
                .ingredients(recipeTestIngredients)
                .prepTime(3)
                .cookTime(0)
                .servings(1)
                .notes(notes)
                .build();


        log.debug("Loading initialization data...");
        Recipe recipe1 = new Recipe();
        recipe1.setCategories(categoryRepository.findByDescription("Seafood"));
        recipe1.setCookTime(5);
        recipe1.setDescription("Crab Casserole");
        recipe1.setDirections("Do the first thing. Then the next thing.");
        recipe1.setServings(2);
        recipe1.setUrl("www.yum.com");
        recipe1.setDifficulty(Difficulty.EASY);
        Set<Ingredient> crabIngredients = new HashSet<>();
        crabIngredients.add(crab);
        recipe1.setIngredients(crabIngredients);

        Recipe recipe2 = new Recipe();
        recipe2.setCategories(categoryRepository.findByDescription("Breakfast"));
        recipe2.setCookTime(6);
        recipe2.setDescription("Pancakes");
        recipe2.setDirections("Get flour. Add Milk. Stir eggs. Dump in sugar. Apply heat");
        recipe2.setServings(100);
        recipe2.setUrl("www.arecipeplace.com");
        recipe2.setDifficulty(Difficulty.HARD);
        Set<Ingredient> pancakesIngredients = new HashSet<>();
        pancakesIngredients.add(eggs);
        pancakesIngredients.add(flour);
        pancakesIngredients.add(milk);
        recipe2.setIngredients(pancakesIngredients);


        recipeRepository.save(recipe1);
        recipeRepository.save(recipe2);
        recipeRepository.save(recipeTest);

    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }
}
