package by.bookify.book.model;

import lombok.Data;

@Data
public class BookCreateDto {
    private String title;
    private String description;
    private String publicationDate;
    private Long genreId;
    private Integer pages;
    private String isbn;
    private String language;
    private Integer storageNum;
    private String author;
    private String publisher;
    private Double price;
}
