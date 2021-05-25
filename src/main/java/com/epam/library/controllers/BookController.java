package com.epam.library.controllers;

import com.epam.library.dto.BookDto;
import com.epam.library.model.Book;
import com.epam.library.service.Implementation.BookServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookServiceImpl bookServiceImpl;

    private ModelMapper modelMapper = new ModelMapper();

    private BookDto convertToDto(Book book) {
        BookDto bookDto = modelMapper.map(book, BookDto.class);
        return bookDto;
    }

    private Book convertToEntity(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        return book;
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        List<Book> books = bookServiceImpl.findAll();
        return books.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable("id") String id) {
        return convertToDto(bookServiceImpl.findByID(id));
    }

    @PostMapping
    public BookDto createBook(BookDto bookDto) {
        Book book = convertToEntity(bookDto);
        Book bookCreated = bookServiceImpl.saveBook(book);
        return convertToDto(bookCreated);
    }

    @PutMapping
    public void updateBook(BookDto bookDto) {
        Book book = convertToEntity(bookDto);
        bookServiceImpl.saveBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") String id) {
        bookServiceImpl.deleteByID(id);
    }

}
