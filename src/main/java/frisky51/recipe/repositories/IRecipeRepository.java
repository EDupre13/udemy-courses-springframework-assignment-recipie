package frisky51.recipe.repositories;

import frisky51.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface IRecipeRepository extends CrudRepository<Recipe, Long> {
}