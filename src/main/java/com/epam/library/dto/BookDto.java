package com.epam.library.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BookDto {

    private String id;

    @NotBlank(message = "name cannot be null!")
    private String name;

    @NotBlank(message = "author cannot be null!")
    private String author;

    @NotNull(message = "year of publishing cannot be null!")
    private int yearOfPublishing;

    public BookDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }


}
