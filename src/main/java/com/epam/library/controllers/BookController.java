package com.epam.library.controllers;

import com.epam.library.dto.BookDto;
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

    private BookDto convertToDto(Book book) {
        BookDto bookDto = new BookDto();
        return bookDto;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") String id) {
        return bookServiceImplfindByID(id);
    }

    @PostMapping
    public String createBook(Book book) {
        bookServiceImpl.saveBook(book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") String id){
        bookServiceImpl.deleteByID(id);
    }

}
