package frisky51.recipe.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class ImageService implements IImageService {
    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
        log.debug("Received img file");
    }
}