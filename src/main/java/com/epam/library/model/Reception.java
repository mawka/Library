package com.epam.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "reception")
public class Reception {

    @Id
    private String id;

    private String idBook;

    private LocalDate dateOfOperation;

    private int quantity;

}
