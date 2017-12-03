package frisky51.recipe.services;

import frisky51.recipe.commands.IngredientCommand;
import frisky51.recipe.converters.IngredientToIngredientCommand;
import frisky51.recipe.domain.Recipe;
import frisky51.recipe.repositories.IRecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientService implements IIngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IRecipeRepository recipeRepository;

    public IngredientService(IngredientToIngredientCommand ingredientToIngredientCommand,
                             IRecipeRepository recipeRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            // TODO: Implement error handling
            log.error("Recipe ID " + recipeId + " not found.");
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if (!ingredientCommandOptional.isPresent()) {
            // TODO: Implement error handling
            log.error("Ingredient ID " + ingredientId + " not found.");
        }

        return ingredientCommandOptional.get();
    }
}