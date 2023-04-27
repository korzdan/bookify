package by.korzun.bookify.book.repository;

import by.korzun.bookify.book.model.Book;
import by.korzun.bookify.genre.model.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAll();
    @Query(nativeQuery = true, value = "SELECT * FROM book b WHERE LOWER(b.title) LIKE CONCAT('%', LOWER(?1), '%')")
    List<Book> findByTitle(String title);
    List<Book> findAllByGenre(Genre genre);
}

