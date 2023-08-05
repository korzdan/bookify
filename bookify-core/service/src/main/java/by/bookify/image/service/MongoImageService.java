package by.bookify.image.service;

import by.bookify.domain.image.exception.ImageNotFoundException;
import by.bookify.domain.image.exception.ImageUploadException;
import by.bookify.domain.image.model.Image;
import by.bookify.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class MongoImageService implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public void uploadImage(Long bookId, MultipartFile file) {
        if(file != null) {
            Image image = buildImage(bookId, file);
            imageRepository.deleteById(bookId);
            imageRepository.insert(image);
        }
    }

    @Override
    public InputStreamResource findImage(Long bookId) {
        Image image = imageRepository
                .findById(bookId)
                .orElseThrow(() -> new ImageNotFoundException("Image with " + bookId + " ID not found."));
        return imageToInputStreamResource(image);
    }

    private Image buildImage(Long bookId, MultipartFile file) {
        return Image.builder()
                .id(bookId)
                .content(getFileContent(bookId, file))
                .build();
    }

    private Binary getFileContent(Long bookId, MultipartFile file) {
        return new Binary(BsonBinarySubType.BINARY, fileToBytes(file, bookId));
    }

    private byte[] fileToBytes(MultipartFile file, Long bookId) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            log.error("Upload image failed for book with id: {}", bookId);
            throw new ImageUploadException("Error occurred during image upload.");
        }
    }

    private InputStreamResource imageToInputStreamResource(Image image) {
        byte[] imageContent = image
                .getContent()
                .getData();
        return new InputStreamResource(new ByteArrayInputStream(imageContent));
    }
}
