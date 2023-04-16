package by.korzun.bookify.publisher.model;

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
@Table(name = "publisher")
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate foundationDate;
    private String website;
    private String email;
    private String phoneNumber;
    private String description;

    @OneToMany
    @JoinTable(
            name = "publishers_books",
            joinColumns = @JoinColumn(name = "publisher_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private List<Book> books = new ArrayList<>();
}
