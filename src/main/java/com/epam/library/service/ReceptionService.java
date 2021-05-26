package com.epam.library.service;

import com.epam.library.model.Reception;

import java.util.List;

public interface ReceptionService {

    void deleteByID(String id);

    List<Reception> findAll();

    Reception findByID(String Id);

    Reception save(Reception reception);

    void update(Reception reception);

}
