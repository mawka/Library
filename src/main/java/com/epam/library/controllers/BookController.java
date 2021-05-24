package com.epam.library.controllers;

import com.epam.library.model.Book;
import com.epam.library.service.Implementation.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook() {
        return bookServiceImpl.getBook();
    }

    @PostMapping
    public String createBook(Book book) {
        bookServiceImpl.saveBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(Book book) {
        return bookServiceImpl.updateBook(Book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") String id){
        bookServiceImpl.deleteByID(id);
    }
}
