package com.epam.library.service.Implementation;

import com.epam.library.model.Student;
import com.epam.library.repository.StudentRepository;
import com.epam.library.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findByID(String id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student with this id not founded!"));
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
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
