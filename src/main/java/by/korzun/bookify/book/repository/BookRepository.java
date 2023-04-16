package by.korzun.bookify.book.repository;

import by.korzun.bookify.book.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAll();
    @Query(nativeQuery = true, value = "SELECT * FROM book b WHERE b.title LIKE '?1%'")
    List<Book> findByTitle(String title);
}

