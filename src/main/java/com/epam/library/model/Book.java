package com.epam.library.model;

import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;
import java.util.Objects;

@Document(collection = "Books")
public class Book {

    @Id
    private String id;

    @NonNull
    private String name;

    @NonNull
    private String author;

    @NonNull
    private int yearOfPublishing;

    public Book() {}

    public Book(String id, String name, String author, int yearOfPublishing) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
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

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Book book = (Book) object;
        return yearOfPublishing == book.yearOfPublishing && id.equals(book.id) && java.util.Objects.equals(name, book.name) && java.util.Objects.equals(author, book.author);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, author, yearOfPublishing);
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
