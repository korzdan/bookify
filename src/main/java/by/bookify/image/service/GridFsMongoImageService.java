package by.bookify.image.service;

import by.bookify.image.exception.ImageNotFoundException;
import by.bookify.image.exception.ImageUploadException;
import by.bookify.image.repository.ImageRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Service
@AllArgsConstructor
public class GridFsMongoImageService implements ImageService {

    private GridFsTemplate gridFsTemplate;

    private static final String metadataId = "metadata.id";

    @Override
    @Transactional
    public void uploadImage(Long bookId, MultipartFile file) {
        if(file != null) {
            DBObject metaData = createBasicDBObject(bookId);
            Query deleteQuery = new Query(Criteria.where(metadataId).is(bookId));
            gridFsTemplate.delete(deleteQuery);
            gridFsTemplate.store(fileToInputStream(file, bookId), file.getName(), file.getContentType(), metaData);
        }
    }

    @Override
    public InputStreamResource findImage(Long bookId) {
        Query findQuery = new Query(Criteria.where(metadataId).is(bookId));
        GridFSFile file = gridFsTemplate.findOne(findQuery);
        if(file == null) {
            throw new ImageNotFoundException("Image with " + bookId + " ID not found.");
        }
        return convertFileToInputStreamResource(file, bookId);
    }

    private DBObject createBasicDBObject(Long bookId) {
        DBObject metaData = new BasicDBObject();
        metaData.put("id", bookId);
        return metaData;
    }

    private InputStreamResource convertFileToInputStreamResource(GridFSFile file, Long bookId) {
        try {
            return new InputStreamResource(gridFsTemplate.getResource(file).getInputStream());
        } catch (IOException e) {
            log.error("Upload image failed for book with id: {}", bookId);
            throw new ImageUploadException("Error occurred during image upload.");
        }
    }

    private InputStream fileToInputStream(MultipartFile file, Long bookId) {
        try {
            return file.getInputStream();
        } catch (IOException e) {
            log.error("Upload image failed for book with id: {}", bookId);
            throw new ImageUploadException("Error occurred during image upload.");
        }
    }
}
