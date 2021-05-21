package com.epam.library.controllers;

import com.epam.library.model.Student;
import com.epam.library.service.Implementation.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @GetMapping("/all")
    public List<Student> getAllStudents(){
        return studentServiceImpl.findAllStudents();
    }

    @GetMapping("/student-create")
    public String createStudentForm(Student student){
        return "student-create";
    }

    @PostMapping
    public String createStudent(Student student) {
        studentServiceImpl.save(student);
        return "redirect:/all";
    }

    @GetMapping("student-delete/{id}")
    public String deleteStudent(@PathVariable("id") String id){
        studentServiceImpl.deleteByID(id);
        return "redirect:/all";
    }
}
