package com.epam.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Books")
public class Book {

    @Id
    public String id;

    public String name;

    public String author;

    public int yearOfPublishing;

    public Book() {}

    public Book(String id, String name, String author, int yearOfPublishing) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", yearOfPublishing=" + yearOfPublishing +
                '}';
    }
}
