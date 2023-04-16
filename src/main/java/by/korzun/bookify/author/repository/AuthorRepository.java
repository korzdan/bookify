package by.korzun.bookify.author.repository;

import by.korzun.bookify.author.model.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM author a WHERE a.full_name LIKE '?1%'")
    List<Author> findByFullName(String fullName);
}
