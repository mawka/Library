package com.epam.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.Objects;

@Document(collection = "Reception")
public class Reception {

    @Id
    private String id;

    private String idBook;

    private LocalDate dateOfOperation;

    private int quantity;

    public Reception() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reception reception = (Reception) o;
        return quantity == reception.quantity && Objects.equals(id, reception.id) && Objects.equals(idBook, reception.idBook) && Objects.equals(dateOfOperation, reception.dateOfOperation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idBook, dateOfOperation, quantity);
    }

    @Override
    public String toString() {
        return "Reception{" +
                "id='" + id + '\'' +
                ", idBook='" + idBook + '\'' +
                ", dateOfOperation=" + dateOfOperation +
                ", quantity=" + quantity +
                '}';
    }
}
