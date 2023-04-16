package by.korzun.bookify.book.contoller;

import by.korzun.bookify.book.model.Book;
import by.korzun.bookify.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/title")
    public ResponseEntity<List<Book>> findByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookService.findByTitle(title));
    }

    @GetMapping("/author")
    public ResponseEntity<List<Book>> findByAuthor(@RequestParam String authorName) {
        return ResponseEntity.ok(bookService.findByAuthorFullName(authorName));
    }

    @GetMapping("publisher")
    public ResponseEntity<List<Book>> findByPublisher(@RequestParam String publisherName) {
        return ResponseEntity.ok(bookService.findByAuthorFullName(publisherName));
    }
}
