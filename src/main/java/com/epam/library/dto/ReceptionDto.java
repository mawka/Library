package com.epam.library.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ReceptionDto {

    private String id;

    @NotNull(message = "book cannot be null!")
    private String idBook;

    @NotNull(message = "date of operation cannot be null!")
    private LocalDate dateOfOperation;

    @NotNull(message = "quantity cannot be null!")
    private int quantity;

    public ReceptionDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public LocalDate getDateOfOperation() {
        return dateOfOperation;
    }

    public void setDateOfOperation(LocalDate dateOfOperation) {
        this.dateOfOperation = dateOfOperation;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
