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
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookServiceImpl.findAll();
    }

    @GetMapping("/book-create")
    public String createBookForm(Book book){
        return "book-create";
    }

    @PostMapping
    public String createBook(Book book) {
        bookServiceImpl.saveBook(book);
        return "redirect:/all";
    }

    @GetMapping("book-delete/{id}")
    public String deleteBook(@PathVariable("id") String id){
        bookServiceImpl.deleteByID(id);
        return "redirect:/all";
    }
}
