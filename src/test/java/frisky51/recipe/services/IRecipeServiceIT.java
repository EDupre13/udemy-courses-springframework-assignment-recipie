package frisky51.recipe.services;

import frisky51.recipe.commands.RecipeCommand;
import frisky51.recipe.converters.RecipeCommandToRecipe;
import frisky51.recipe.converters.RecipeToRecipeCommand;
import frisky51.recipe.domain.Recipe;
import frisky51.recipe.repositories.IRecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IRecipeServiceIT {

    public static final String NEW_DESCRIPTION = "new desc";

    @Autowired
    IRecipeService recipeService;

    @Autowired
    IRecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Transactional
    @Test
    public void testSaveOfDescription() throws Exception {
        // Given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();

        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        // When
        testRecipeCommand.setDescription(NEW_DESCRIPTION);

        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        // Then
        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
    }
}