package com.epam.library.service.Implementation;

import com.epam.library.model.Book;
import com.epam.library.repository.BookRepository;
import com.epam.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book findByID(String id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book with this id not founded!"));
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book saveBook(Book book) {
        bookRepository.save(book);
        return book;
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteByID(String id) {
        bookRepository.deleteById(id);
    }
}
