package com.epam.library.service;

import com.epam.library.model.Student;

import java.util.List;

public interface StudentService {

    Student findByID(String Id);

    List<Student> findAllStudents();

    void deleteByID(String id);

    void save(Student student);
}
