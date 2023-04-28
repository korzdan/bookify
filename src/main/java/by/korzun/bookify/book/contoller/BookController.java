package by.korzun.bookify.book.contoller;

import by.korzun.bookify.book.model.Book;
import by.korzun.bookify.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    @GetMapping("/publisher")
    public ResponseEntity<List<Book>> findByPublisher(@RequestParam String publisherName) {
        return ResponseEntity.ok(bookService.findByAuthorFullName(publisherName));
    }

    @GetMapping("/img/{name}")
    public ResponseEntity<InputStreamResource> getBookImage(@PathVariable String name) {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(
                        Objects.requireNonNull(getClass().getResourceAsStream("/images/" + name)))
                );
    }
}
