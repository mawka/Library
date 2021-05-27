package com.epam.library.service;

import com.epam.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    void deleteByID(String id);

    Book findByID(String Id);

    Book saveBook(Book book);

    void updateBook(Book book);

    List<Book> findAll();

}
