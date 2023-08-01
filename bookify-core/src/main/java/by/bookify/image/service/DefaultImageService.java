package by.bookify.image.service;

import by.bookify.image.exception.ImageNotFoundException;
import by.bookify.image.exception.ImageUploadException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Slf4j
public class DefaultImageService implements ImageService {

    private static final String JPG_IMAGE_EXTENSION = ".jpg";

    private static final String PATH_TO_UPLOAD_IMAGE = System.getProperty("user.dir") + "/src/main/images/";

    @Override
    public void uploadImage(Long bookId, MultipartFile file) {
        try {
            Files.write(Paths.get(definePathForImage(bookId)), file.getBytes());
        } catch (IOException e) {
            log.error("Upload image failed for book with id: {}", bookId);
            throw new ImageUploadException("Error occurred during image upload.");
        }
    }

    @Override
    public InputStreamResource findImage(Long bookId) {
        try {
            return new InputStreamResource(
                    new FileInputStream(definePathForImage(bookId))
            );
        } catch (FileNotFoundException e) {
            throw new ImageNotFoundException("Image with " + bookId + " ID not found.");
        }
    }

    private String definePathForImage(Long bookId) {
        return PATH_TO_UPLOAD_IMAGE + "/" + bookId + JPG_IMAGE_EXTENSION;
    }
}
