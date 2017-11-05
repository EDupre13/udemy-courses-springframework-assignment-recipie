package frisky51.recipe.services;

import frisky51.recipe.domain.Recipe;
import frisky51.recipe.repositories.IRecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeServiceTest {

    RecipeService recipeService;

    @Mock
    IRecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeService(recipeRepository);
    }

    @Test
    public void getRecipes() throws Exception {
        Recipe recipe = new Recipe();

        HashSet recipeData = new HashSet();
        recipeData.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipeData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }
}