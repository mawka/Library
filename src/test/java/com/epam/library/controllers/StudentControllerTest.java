package com.epam.library.controllers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.epam.library.dto.BookDto;
import com.epam.library.dto.StudentDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentController studentController;

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get("/students")).andExpect(status().isOk());
    }

    @Test
    public void getStudentsWhenStudentNotFoundedThenThrowException() throws Exception {
        assertThatThrownBy(() -> mockMvc.perform(get("/students/123")).andExpect(status().isOk())).hasCause(new RuntimeException("Student with this id not founded!"));
    }

    @Test
    public void getStudentWhenStudentNotFoundedThenReturnPage() throws Exception {
        assertThatThrownBy(() -> mockMvc.perform(get("/students/123")).andExpect(status().isOk())).hasCause(new RuntimeException("Student with this id not founded!"));
    }

    @Test
    public void createStudentWhenStudentIsCorrectThenCreateStudent() throws Exception {
        String uri = "/students";
        StudentDto studentDto = new StudentDto();
        studentDto.setId("1");
        studentDto.setFirstName("a");
        studentDto.setLastName("b");

        ObjectMapper objectMapper = new ObjectMapper();
        String inputJson = objectMapper.writeValueAsString(studentDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void updateStudentWhenStudentIsCorrectThenUpdateStudent() throws Exception {
        String uri = "/students";
        StudentDto studentDto = new StudentDto();
        studentDto.setId("1");
        studentDto.setFirstName("a");
        studentDto.setLastName("b");

        ObjectMapper objectMapper = new ObjectMapper();
        String inputJson = objectMapper.writeValueAsString(studentDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void deleteStudentWhenStudentIsCorrectThenDeleteStudent() throws Exception {
        String uri = "/students/2";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}