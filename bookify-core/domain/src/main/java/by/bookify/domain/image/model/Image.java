package by.bookify.domain.image.model;

import lombok.Builder;
import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "images")
public class Image {
    @Id
    private Long id;

    private Binary content;
}
