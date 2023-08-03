package by.bookify.domain.book.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@Accessors(chain = true)
public class BookCreateDto {
    @NotNull(message = "title must be not null")
    @NotEmpty(message = "title must be not empty")
    private String title;

    @NotNull(message = "description must be not null")
    @NotEmpty(message = "description must be not empty")
    private String description;

    @NotNull(message = "publicationDate must be not null")
    @NotEmpty(message = "publicationDate must be not empty")
    private String publicationDate;

    @NotNull(message = "genreId must be not null")
    private Long genreId;

    @NotNull(message = "pages must be not null")
    @Positive(message = "pages must be positive")
    private Integer pages;

    @NotNull(message = "isbn must be not null")
    @NotEmpty(message = "isbn must must be not empty")
    private String isbn;

    @NotNull(message = "language must be not null")
    @NotEmpty(message = "language must be not empty")
    private String language;

    @NotNull
    @PositiveOrZero(message = "storageNum must be positive or 0")
    private Integer storageNum;

    @NotNull(message = "author must be not null")
    @NotEmpty(message = "author must be not empty")
    private String author;

    @NotNull(message = "publisher must be not null")
    @NotEmpty(message = "publisher must be not empty")
    private String publisher;

    @NotNull(message = "price must be not null")
    @Positive(message = "price must be not null")
    private Double price;
}
