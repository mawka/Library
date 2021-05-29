package com.epam.library.service;

import com.epam.library.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {

    Student findByID(String Id);

    void deleteByID(String id);

    void save(Student student);

    Page<Student> findAllStudents(Pageable pageable);
}
