package com.epam.library.dto;

import javax.validation.constraints.NotNull;

public class StudentDto {

    private String id;

    @NotNull(message = "first name cannot be null!")
    private String firstName;

    @NotNull(message = "last name cannot be null!")
    private String lastName;

    public StudentDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
