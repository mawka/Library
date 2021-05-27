package com.epam.library.service;

import com.epam.library.model.LendBooks;

import java.util.List;

public interface LendBooksService {

    void deleteByID(String id);

    List<LendBooks> findAll();

    LendBooks findByID(String Id);

    void save(LendBooks lendBooks);

    void update(LendBooks lendBooks);
}
