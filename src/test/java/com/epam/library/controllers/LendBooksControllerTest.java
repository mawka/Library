package com.epam.library.controllers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.epam.library.dto.LendBooksDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.bson.types.ObjectId;
import org.junit.Ignore;
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

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LendBooksControllerTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LendBooksController lendBooksController;

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get("/lending")).andExpect(status().isOk());
    }

    @Test
    public void getLendingWhenLendingNotFoundedThenThrowException() throws Exception {
        assertThatThrownBy(() -> mockMvc.perform(get("/lending/123")).andExpect(status().isOk())).hasCause(new RuntimeException("Operation with this id not founded!"));
    }

    @Test
    public void getLendingWhenBookNotFoundedThenReturnPage() throws Exception {
        assertThatThrownBy(() -> mockMvc.perform(get("/lending/123")).andExpect(status().isOk())).hasCause(new RuntimeException("Operation with this id not founded!"));
    }

    @Ignore
    @Test
    public void createLendingWhenLendingIsCorrectThenCreateLending() throws Exception {
        String uri = "/lending";
        LendBooksDto lendBooksDto = new LendBooksDto();
        lendBooksDto.setIdBook(new ObjectId().toString());
        lendBooksDto.setId(new ObjectId().toString());
        lendBooksDto.setDateOfOperation(LocalDate.of(2016,10,1));
        lendBooksDto.setExpiredDate(LocalDate.of(2016,10,1));
        lendBooksDto.setIdStudent(new ObjectId().toString());

        ObjectMapper objectMapper = new ObjectMapper();
        String inputJson = objectMapper.writeValueAsString(lendBooksDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Ignore
    @Test
    public void updateLendingWhenLendingIsCorrectThenUpdateLending() throws Exception {
        String uri = "/lending";
        LendBooksDto lendBooksDto = new LendBooksDto();
        lendBooksDto.setIdBook(new ObjectId().toString());
        lendBooksDto.setId(new ObjectId().toString());
        lendBooksDto.setDateOfOperation(LocalDate.of(2016,10,1));
        lendBooksDto.setExpiredDate(LocalDate.of(2022,10,1));
        lendBooksDto.setIdStudent(new ObjectId().toString());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);

        String inputJson = objectMapper.writeValueAsString(lendBooksDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void deleteLendingWhenLendingIsCorrectThenDeleteLending() throws Exception {
        String uri = "/lending/2";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}