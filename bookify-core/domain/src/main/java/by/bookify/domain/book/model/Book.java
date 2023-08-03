package by.bookify.domain.book.model;

import by.bookify.domain.genre.model.Genre;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "book")
@Setter
@Getter
@Accessors(chain = true)
@EqualsAndHashCode
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate publicationDate;
    private Integer pages;
    private String isbn;
    private String author;
    private String publisher;
    @Enumerated(value = EnumType.STRING)
    private BookLanguage language;
    private Double price;
    private Integer storageNum;
    private Integer orderNum;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Genre genre;
}
