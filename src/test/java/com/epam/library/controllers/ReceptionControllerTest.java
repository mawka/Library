package com.epam.library.controllers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.epam.library.dto.BookDto;
import com.epam.library.dto.ReceptionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class ReceptionControllerTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReceptionController receptionController;

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get("/reception")).andExpect(status().isOk());
    }

    @Test
    public void getReceptionWhenReceptionNotFoundedThenThrowException() throws Exception {
        assertThatThrownBy(() -> mockMvc.perform(get("/reception/123")).andExpect(status().isOk())).hasCause(new RuntimeException("This record doesn't exist!"));
    }

    @Test
    public void getReceptionWhenReceptionNotFoundedThenReturnPage() throws Exception {
        assertThatThrownBy(() -> mockMvc.perform(get("/reception/123")).andExpect(status().isOk())).hasCause(new RuntimeException("This record doesn't exist!"));
    }

    @Ignore
    @Test
    public void createReceptionWhenReceptionIsCorrectThenCreateBook() throws Exception {
        String uri = "/reception";
        ReceptionDto receptionDto = new ReceptionDto();
        receptionDto.setId(new ObjectId().toString());
        receptionDto.setIdBook(new ObjectId().toString());
        receptionDto.setQuantity(1);
        receptionDto.setDateOfOperation(LocalDate.of(2014,10,1));


        ObjectMapper objectMapper = new ObjectMapper();
        String inputJson = objectMapper.writeValueAsString(receptionDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Ignore
    @Test
    public void updateReceptionWhenReceptionIsCorrectThenUpdateBook() throws Exception {
        String uri = "/reception";
        BookDto bookDto = new BookDto();
        bookDto.setId(new ObjectId().toString());
        bookDto.setAuthor("R");
        bookDto.setYearOfPublishing(2016);
        bookDto.setName("Ginger");

        ObjectMapper objectMapper = new ObjectMapper();
        String inputJson = objectMapper.writeValueAsString(bookDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void deleteReceptionWhenReceptionIsCorrectThenDeleteReception() throws Exception {
        String uri = "/reception/2";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}