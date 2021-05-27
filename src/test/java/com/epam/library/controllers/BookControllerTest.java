package com.epam.library.controllers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.epam.library.dto.BookDto;
import com.epam.library.model.Book;
import com.epam.library.service.Implementation.BookServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookController bookController;

    @Autowired
    private BookServiceImpl bookService;


    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get("/books")).andExpect(status().isOk());
    }

    @Test
    public void getBookWhenBookNotFoundedThenThrowException() throws Exception {
        assertThatThrownBy(() -> mockMvc.perform(get("/books/123")).andExpect(status().isOk())).hasCause(new RuntimeException("Book with this id not founded!"));
    }

    @Test
    public void getBookWhenBookNotFoundedThenReturnPage() throws Exception {
        assertThatThrownBy(() -> mockMvc.perform(get("/books/123")).andExpect(status().isOk())).hasCause(new RuntimeException("Book with this id not founded!"));
    }

    @Ignore
    @Test
    public void createBookWhenBookIsCorrectThenCreateBook() throws Exception {
        String uri = "/books";
        BookDto bookDto = new BookDto();
        bookDto.setId("3");
        bookDto.setAuthor("R");
        bookDto.setYearOfPublishing(2016);
        bookDto.setName("Ginger");

        ObjectMapper objectMapper = new ObjectMapper();
        String inputJson = objectMapper.writeValueAsString(objectMapper);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void updateBookWhenBookIsCorrectThenUpdateBook() throws Exception {
        String uri = "/books";
        BookDto bookDto = new BookDto();
        bookDto.setId("3");
        bookDto.setAuthor("R");
        bookDto.setYearOfPublishing(2016);
        bookDto.setName("Ginger");

        ObjectMapper objectMapper = new ObjectMapper();
        String inputJson = objectMapper.writeValueAsString(bookDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);


//        String uri = "/books";
//        Book book = new Book();
//        book.setId("3");
//        book.setAuthor("R");
//        book.setYearOfPublishing(2016);
//        book.setName("Ginger");
//
//        when(bookService.saveBook(any(Book.class))).thenReturn(book);
//        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(new ObjectMapper().writeValueAsString(book))
//                .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void deleteBookWhenBookIsCorrectThenDeleteBook() throws Exception {
        String uri = "/books/" + UUID.randomUUID().toString();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri))
              //  .andExpect(status());
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}