package by.korzun.bookify.book.model;

import by.korzun.bookify.author.model.Author;
import by.korzun.bookify.genre.model.Genre;
import by.korzun.bookify.publisher.model.Publisher;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "book")
@Setter
@Getter
@Accessors(chain = true)
@EqualsAndHashCode
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate publicationDate;
    private Integer pages;
    private String isbn;
    @Enumerated(value = EnumType.STRING)
    private BookLanguage language;
    private double price;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Genre genre;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Author author;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Publisher publisher;
}
