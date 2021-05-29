package com.epam.library.service.Implementation;

import com.epam.library.model.Student;
import com.epam.library.repository.StudentRepository;
import com.epam.library.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findByID(String id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student with this id not founded!"));
    }

    @Override
    public Page<Student> findAllStudents(Pageable pageable) {
        return studentRepository.findAllStudents(pageable);
    }

    @Override
    public void deleteByID(String id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

}
