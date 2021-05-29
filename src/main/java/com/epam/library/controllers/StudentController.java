package com.epam.library.controllers;

import static com.epam.library.converter.StudentConverter.convertToDto;
import static com.epam.library.converter.StudentConverter.convertToEntity;
import com.epam.library.converter.StudentConverter;
import com.epam.library.dto.StudentDto;
import com.epam.library.model.Student;
import com.epam.library.service.Implementation.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @GetMapping()
    public Page<StudentDto> getAllStudents(Pageable pageable) {
        Page<Student> studentList = studentServiceImpl.findAllStudents(pageable);
        List<StudentDto> studentsDto = studentList.stream()
                .map(StudentConverter::convertToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(studentsDto);
  }

    @GetMapping("/{id}")
    public StudentDto getStudent(@PathVariable("id") String id) {
        return convertToDto(studentServiceImpl.findByID(id));
    }

    @PostMapping
    public StudentDto createStudent(@RequestBody @Valid StudentDto studentDto) {
        Student student = convertToEntity(studentDto);
        studentServiceImpl.save(student);
        return convertToDto(student);
    }

    @PutMapping
    public void updateStudent(@RequestBody @Valid StudentDto studentDto) {
        Student student = convertToEntity(studentDto);
        studentServiceImpl.save(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") String id) {
        studentServiceImpl.deleteByID(id);
    }
}
