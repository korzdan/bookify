package by.korzun.bookify.author.model;

import by.korzun.bookify.book.model.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String description;
    private String website;
    private LocalDate birthDate;

    @OneToMany
    @JoinTable(
            name = "authors_books",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private List<Book> books = new ArrayList<>();
}
