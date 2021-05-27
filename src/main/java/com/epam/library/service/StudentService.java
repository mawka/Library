package com.epam.library.service;

import com.epam.library.model.Student;
import org.springframework.data.domain.Page;

public interface StudentService {

    Student findByID(String Id);

    void deleteByID(String id);

    void save(Student student);

    Page<Student> findAllStudents();
}
