package com.epam.library.repository;

import com.epam.library.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
    Student findById(Long id);
}
