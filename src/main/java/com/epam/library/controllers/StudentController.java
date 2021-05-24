package com.epam.library.controllers;

import com.epam.library.model.Student;
import com.epam.library.service.Implementation.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @GetMapping()
    public List<Student> getAllStudents(){
        return studentServiceImpl.findAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") String id){
        return studentServiceImpl.findByID(id);
    }

    @PostMapping
    public void createStudent(Student student) {
        studentServiceImpl.save(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") String id){
        studentServiceImpl.deleteByID(id);
    }
}
