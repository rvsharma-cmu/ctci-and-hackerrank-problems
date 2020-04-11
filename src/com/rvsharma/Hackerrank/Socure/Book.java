package com.rvsharma.Hackerrank.Socure;

//import javax.validation.*;
//import javax.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;


public class Book {
    private String title;

    private List<Chapter> chapters;

    private List<String> authors;

    private String isbn;

    private LocalDate firstEditionPublishDate;

    private LocalDate lastEditionPublishDate;

    private Integer numberOfPages;

    Book(String title,
         List<Chapter> chapters,
         List<String> authors,
         String isbn,
         LocalDate firstEditionPublishDate,
         LocalDate lastEditionPublishDate,
         Integer numberOfPages) {

        this.title = title;
        this.chapters = chapters;
        this.authors = authors;
        this.isbn = isbn;
        this.firstEditionPublishDate = firstEditionPublishDate;
        this.lastEditionPublishDate = lastEditionPublishDate;
        this.numberOfPages = numberOfPages;
    }

    String getTitle() {
        return title;
    }

    List<Chapter> getChapters() {
        return chapters;
    }

    List<String> getAuthors() {
        return authors;
    }

    String getIsbn() {
        return isbn;
    }

    LocalDate getFirstEditionPublishDate() {
        return firstEditionPublishDate;
    }

    LocalDate getLastEditionPublishDate() {
        return lastEditionPublishDate;
    }

    Integer getNumberOfPages() {
        return numberOfPages;
    }
}


