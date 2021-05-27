package com.epam.library.service.Implementation;

import static java.util.Optional.of;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.epam.library.LibraryApplication;
import com.epam.library.model.Book;
import com.epam.library.repository.BookRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class BookServiceImplTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void findByIDWhenBookIsFoundedThenReturnBook() {
        UUID actualUUID = UUID.randomUUID();
        Book expectedBook = new Book();
        expectedBook.setId(actualUUID.toString());
        when(bookRepository.findById(actualUUID.toString()))
                .thenReturn(of(of(expectedBook)).get());
        Book actualBook = bookService.findByID(actualUUID.toString());
        assertSame(expectedBook, actualBook);
    }

    @Test
    public void findByIDWhenBookItsNotFoundedThenThrowException() {
        UUID UUIDExpected = UUID.randomUUID();

        String expectedException = "Book with this id not founded!";
        exception.expect(RuntimeException.class);
        exception.expectMessage(expectedException);
        bookService.findByID(UUIDExpected.toString());
    }

    @Test
    public void saveBookWhenSaveThenReturnBook(){
        Book expectedBook = new Book();
        UUID expectedUUID = UUID.randomUUID();
        expectedBook.setId(expectedUUID.toString());
        when(bookRepository.save(expectedBook)).thenReturn(expectedBook);
        Book actualBook = bookService.saveBook(expectedBook);
        assertSame(expectedBook,actualBook);
    }

    @Test
    public void updateBookWhenUpdateBookThenSave(){
        Book expectedBook = new Book();
        UUID expectedUUID = UUID.randomUUID();
        expectedBook.setId(expectedUUID.toString());
        when(bookRepository.save(expectedBook)).thenReturn(expectedBook);
        bookService.updateBook(expectedBook);
        when(bookRepository.findById(expectedUUID.toString())).thenReturn(of(expectedBook));
        assertSame(expectedBook,bookRepository.findById(expectedUUID.toString()).get());
    }

    @Test
    public void findAllBooksWhenFoundedThenReturnPages(){
        bookService.findAllBooks(Pageable.unpaged());
        verify(bookRepository).findAllBooks(Pageable.unpaged());
    }

    @Test
    public void deleteByIDWhenBookFoundedThenDelete() {
        UUID expectedUUID = UUID.randomUUID();
        bookService.deleteByID(expectedUUID.toString());
        verify(bookRepository).deleteById(expectedUUID.toString());
    }


}