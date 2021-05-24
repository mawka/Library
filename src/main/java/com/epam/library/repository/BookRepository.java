package com.epam.library.repository;

import com.epam.library.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
}
