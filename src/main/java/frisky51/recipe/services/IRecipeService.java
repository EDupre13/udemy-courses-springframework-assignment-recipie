package frisky51.recipe.services;

import frisky51.recipe.domain.Recipe;

import java.util.Set;

public interface IRecipeService {

    Set<Recipe> getRecipes();
}