package com.epam.library.service;

import com.epam.library.model.Book;

import java.util.List;

public interface BookService {

    void deleteByID(String id);

    List<Book> findAll();

    Book findByID(String Id);

    Book saveBook(Book book);

    void updateBook(Book book);

}
