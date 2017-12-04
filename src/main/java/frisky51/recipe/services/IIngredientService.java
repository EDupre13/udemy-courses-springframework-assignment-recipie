package frisky51.recipe.services;

import frisky51.recipe.commands.IngredientCommand;

public interface IIngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
}