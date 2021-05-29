package com.epam.library.controllers;

import static com.epam.library.converter.BookConverter.convertToDto;
import static com.epam.library.converter.BookConverter.convertToEntity;
import com.epam.library.converter.BookConverter;
import com.epam.library.dto.BookDto;
import com.epam.library.model.Book;
import com.epam.library.service.Implementation.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookServiceImpl bookServiceImpl;

    @Autowired
    private BookController(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    @GetMapping
    public Page<BookDto> getAll(Pageable pegaable) {
        Page<Book> books = bookServiceImpl.findAllBooks(pegaable);
        List<BookDto> booksDto = books.stream()
                .map(BookConverter::convertToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(booksDto);
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable("id") String id) {
        return convertToDto(bookServiceImpl.findByID(id));
    }

    @PostMapping
    public BookDto createBook(@RequestBody @Valid BookDto bookDto) {
        Book book = convertToEntity(bookDto);
        Book bookCreated = bookServiceImpl.saveBook(book);
        return convertToDto(bookCreated);
    }

    @PutMapping
    public void updateBook(@RequestBody @Valid BookDto bookDto) {
        Book book = convertToEntity(bookDto);
        bookServiceImpl.saveBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") String id) {
        bookServiceImpl.deleteByID(id);
    }

}
