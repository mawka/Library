package com.epam.library.dto;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class LendBooksDto {
    @Id
    private String id;

    @NotNull(message = "book cannot be null!")
    private String idBook;

    @NotNull(message = "student cannot be null!")
    private String idStudent;

    @NotNull(message = "date of operation cannot be null!")
    private LocalDate dateOfOperation;

    @Future(message = "expired date cannot be in past!")
    private LocalDate expiredDate;

    public LendBooksDto() {
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

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public LocalDate getDateOfOperation() {
        return dateOfOperation;
    }

    public void setDateOfOperation(LocalDate dateOfOperation) {
        this.dateOfOperation = dateOfOperation;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }


}
