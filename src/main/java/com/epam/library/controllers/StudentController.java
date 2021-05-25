package com.epam.library.controllers;

import com.epam.library.dto.StudentDto;
import com.epam.library.model.Student;
import com.epam.library.service.Implementation.StudentServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    private ModelMapper modelMapper = new ModelMapper();

    private StudentDto convertToDto(Student student) {
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);
        return studentDto;
    }

    private Student convertToEntity(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        return student;
    }

    @GetMapping()
    public List<StudentDto> getAllStudents() {
        List<Student> studentList = studentServiceImpl.findAllStudents();
        return studentList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StudentDto getStudent(@PathVariable("id") String id) {
        return convertToDto(studentServiceImpl.findByID(id));
    }

    @PostMapping
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = convertToEntity(studentDto);
        studentServiceImpl.save(student);
        return convertToDto(student);
    }

    @PutMapping
    public void updateStudent(StudentDto studentDto) {
        Student student = convertToEntity(studentDto);
        studentServiceImpl.save(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") String id) {
        studentServiceImpl.deleteByID(id);
    }
}
