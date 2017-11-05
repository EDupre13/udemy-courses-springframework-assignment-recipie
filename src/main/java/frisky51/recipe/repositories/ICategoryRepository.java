package frisky51.recipe.repositories;

import frisky51.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ICategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}