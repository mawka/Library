package com.epam.library.converter;

import com.epam.library.dto.StudentDto;
import com.epam.library.model.Student;
import org.modelmapper.ModelMapper;

public class StudentConverter {

    private static ModelMapper modelMapper = new ModelMapper();

    public static StudentDto convertToDto(Student student) {
        return modelMapper.map(student, StudentDto.class);
    }

    public static Student convertToEntity(StudentDto studentDto) {
        return modelMapper.map(studentDto, Student.class);
    }
}
