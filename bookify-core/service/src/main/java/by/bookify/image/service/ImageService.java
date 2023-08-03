package by.bookify.image.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void uploadImage(Long bookId, MultipartFile file);

    InputStreamResource findImage(Long bookId);
}
