package by.korzun.bookify.publisher.repository;

import by.korzun.bookify.publisher.model.Publisher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM publisher p WHERE LOWER(p.name) LIKE CONCAT('%', LOWER(?1), '%')")
    List<Publisher> findByName(String fullName);
}
