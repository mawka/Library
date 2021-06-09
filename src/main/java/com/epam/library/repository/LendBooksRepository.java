package com.epam.library.repository;

import com.epam.library.model.LendBooks;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LendBooksRepository extends MongoRepository<LendBooks, String> {
}
