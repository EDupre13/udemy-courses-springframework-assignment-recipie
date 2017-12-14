package frisky51.recipe.services;

import frisky51.recipe.domain.Recipe;
import frisky51.recipe.repositories.IRecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Slf4j
@Service
public class ImageService implements IImageService {

    private final IRecipeRepository recipeRepository;

    public ImageService(IRecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file) {
        try {

            Recipe recipe = recipeRepository.findById(recipeId).get();

            Byte[] bytes = new Byte[file.getBytes().length];

            int i = 0;
            for (byte b : file.getBytes()) {
                bytes[i++] = b;
            }

            recipe.setImage(bytes);

            recipeRepository.save(recipe);

        } catch (IOException e) {
            // TODO: handle better

            log.error("Error occurred", e);
            e.printStackTrace();
        }
    }
}