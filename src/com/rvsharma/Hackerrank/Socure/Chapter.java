package com.rvsharma.Hackerrank.Socure;

public class Chapter {
    private String title;

    private Integer numberOfPages;

    Chapter(String title, Integer numberOfPages) {
        this.title = title;
        this.numberOfPages = numberOfPages;
    }

    public String toString() {
        return "Chapter{" +
                "title='" + title + '\'' +
                ", numberOfPages=" + numberOfPages +
                '}';
    }

    String getTitle() {
        return title;
    }

    Integer getNumberOfPages() {
        return numberOfPages;
    }
}
