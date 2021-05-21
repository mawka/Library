package com.epam.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Students")
public class Student {
    @Id
    public String id;

    public String firstName;
    public String lastName;

    public Student() {}

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Student[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
}
