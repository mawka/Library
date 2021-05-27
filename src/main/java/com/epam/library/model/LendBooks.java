package com.epam.library.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Objects;

@Document(collection = "LendBooks")
public class LendBooks {

    @Id
    private String id;

    private String idBook;

    private String idStudent;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOfOperation;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate expiredDate;

    public LendBooks() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LendBooks lendBooks = (LendBooks) o;
        return Objects.equals(id, lendBooks.id) && Objects.equals(idBook, lendBooks.idBook) && Objects.equals(idStudent, lendBooks.idStudent) && Objects.equals(dateOfOperation, lendBooks.dateOfOperation) && Objects.equals(expiredDate, lendBooks.expiredDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idBook, idStudent, dateOfOperation, expiredDate);
    }

    @Override
    public String toString() {
        return "LendBooks{" +
                "id='" + id + '\'' +
                ", idBook='" + idBook + '\'' +
                ", idStudent='" + idStudent + '\'' +
                ", dateOfOperation=" + dateOfOperation +
                ", expiredDate=" + expiredDate +
                '}';
    }
}
