package by.bookify.image.repository;

import by.bookify.image.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, Long> {
}
