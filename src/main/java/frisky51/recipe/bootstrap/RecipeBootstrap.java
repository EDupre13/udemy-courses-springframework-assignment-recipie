package frisky51.recipe.bootstrap;

import frisky51.recipe.domain.*;
import frisky51.recipe.enumerations.Difficulty;
import frisky51.recipe.repositories.ICategoryRepository;
import frisky51.recipe.repositories.IRecipeRepository;
import frisky51.recipe.repositories.IUnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final ICategoryRepository categoryRepository;
    private final IRecipeRepository recipeRepository;
    private final IUnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(ICategoryRepository categoryRepository,
                           IRecipeRepository recipeRepository,
                           IUnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        // Get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        // Get Optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();

        // Get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected category not found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if(!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected category not found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        // First recipe
        Recipe recipe1 = new Recipe();
        recipe1.setDescription("Recipe1");
        recipe1.setPrepTime(10);
        recipe1.setCookTime(0);
        recipe1.setDifficulty(Difficulty.EASY);
        recipe1.setDirections("1. Do this\n" +
                "2. Do that\n" +
                "3. Eat");

        Notes r1notes = new Notes();
        r1notes.setRecipeNotes("Get ready for the best diarrhea ever ;)");
        r1notes.setRecipe(recipe1);

        recipe1.setNotes(r1notes);

        recipe1.getIngredients().add(new Ingredient("dog shit", new BigDecimal(2), eachUom, recipe1));
        recipe1.getIngredients().add(new Ingredient("something", new BigDecimal(".5"), teaSpoonUom, recipe1));
        recipe1.getIngredients().add(new Ingredient("this", new BigDecimal(2), tableSpoonUom, recipe1));
        recipe1.getIngredients().add(new Ingredient("that", new BigDecimal(2), eachUom, recipe1));
        recipe1.getIngredients().add(new Ingredient("another thing", new BigDecimal(2), eachUom, recipe1));
        recipe1.getIngredients().add(new Ingredient("cat shit", new BigDecimal(2), dashUom, recipe1));
        recipe1.getIngredients().add(new Ingredient("dog piss", new BigDecimal(2), pintUom, recipe1));
        recipe1.getIngredients().add(new Ingredient("rat eyes", new BigDecimal(2), cupUom, recipe1));

        recipe1.getCategories().add(americanCategory);
        recipe1.getCategories().add(mexicanCategory);

        // Add to return list
        recipes.add(recipe1);

        // Second recipe
        Recipe recipe2 = new Recipe();
        recipe2.setDescription("Recipe2");
        recipe2.setPrepTime(20);
        recipe2.setCookTime(10);
        recipe2.setDifficulty(Difficulty.HARD);
        recipe2.setDirections("1. Do this\n" +
                "2. Do that\n" +
                "3. Cook\n" +
                "4. Eat");

        Notes r2notes = new Notes();
        r2notes.setRecipeNotes("This will kill you ><");
        r2notes.setRecipe(recipe2);

        recipe2.setNotes(r2notes);

        recipe2.getIngredients().add(new Ingredient("this", new BigDecimal(2), tableSpoonUom, recipe2));
        recipe2.getIngredients().add(new Ingredient("that", new BigDecimal(2), eachUom, recipe2));
        recipe2.getIngredients().add(new Ingredient("another thing", new BigDecimal(2), eachUom, recipe2));
        recipe2.getIngredients().add(new Ingredient("dog shit", new BigDecimal(2), eachUom, recipe2));
        recipe2.getIngredients().add(new Ingredient("something", new BigDecimal(".5"), teaSpoonUom, recipe2));
        recipe2.getIngredients().add(new Ingredient("cat shit", new BigDecimal(2), dashUom, recipe2));
        recipe2.getIngredients().add(new Ingredient("dog piss", new BigDecimal(2), pintUom, recipe2));
        recipe2.getIngredients().add(new Ingredient("rat eyes", new BigDecimal(2), cupUom, recipe2));

        recipe2.getCategories().add(americanCategory);
        recipe2.getCategories().add(mexicanCategory);

        // Add to return list
        recipes.add(recipe2);

        return recipes;
    }
}
