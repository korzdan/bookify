package by.bookify.image.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String JPG_IMAGE_EXTENSION = ".jpg";

    void uploadImage(Long bookId, MultipartFile file);

    InputStreamResource findImage(Long bookId);
}
