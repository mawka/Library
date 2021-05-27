package com.epam.library.dto;

public class BookQuantityDto {

    private String id;

    private String idBook;

    private int quantity;

    public BookQuantityDto() {
    }

    public String getId() {
        return id;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
