package com.epam.library.service.Implementation;

import static java.util.Optional.of;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.epam.library.LibraryApplication;
import com.epam.library.model.LendBooks;
import com.epam.library.repository.LendBooksRepository;
import org.bson.types.ObjectId;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
@DataMongoTest
public class LendBooksServiceImplTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock
    private LendBooksRepository lendBooksRepository;

    @InjectMocks
    private LendBooksServiceImpl lendBooksService;

    @InjectMocks
    private ReceptionServiceImpl receptionService;

    @Test
    public void findByIDWhenReceptionIsFoundedThenReturnLending() {
        UUID actualUUID = UUID.randomUUID();
        LendBooks expectedLending = new LendBooks();
        expectedLending.setId(actualUUID.toString());
        when(lendBooksRepository.findById(actualUUID.toString()))
                .thenReturn(of(of(expectedLending)).get());
        LendBooks actualLendBooks = lendBooksService.findByID(actualUUID.toString());
        assertSame(expectedLending, actualLendBooks);
    }

    @Test
    public void findByIDWhenLendBooksItsNotFoundedThenThrowException() {
        UUID UUIDExpected = UUID.randomUUID();

        String expectedException = "Operation with this id not founded!";
        exception.expect(RuntimeException.class);
        exception.expectMessage(expectedException);
        lendBooksService.findByID(UUIDExpected.toString());
    }

    @Ignore
    @Test
    public void saveLendBooksWhenQuantityIsEnoughThenSave() {
        LendBooks expectedLendBooks = new LendBooks();
        ObjectId expectedUUID = new ObjectId();
        expectedLendBooks.setId(expectedUUID.toString());
        when(receptionService.findBookQuantityByBookID(expectedUUID.toString())).thenReturn(4);
        lendBooksService.save(expectedLendBooks);
        verify(lendBooksRepository).save(expectedLendBooks);
    }

    @Test
    public void updateLendBooksWhenUpdateLendBooksThenSave() {
        LendBooks expectedLendBooks = new LendBooks();
        UUID expectedUUID = UUID.randomUUID();
        expectedLendBooks.setId(expectedUUID.toString());
        when(lendBooksRepository.save(expectedLendBooks)).thenReturn(expectedLendBooks);
        lendBooksService.update(expectedLendBooks);
        when(lendBooksRepository.findById(expectedUUID.toString())).thenReturn(of(expectedLendBooks));
        assertSame(expectedLendBooks, lendBooksRepository.findById(expectedUUID.toString()).get());
    }

    @Test
    public void deleteByIDWhenLendBooksFoundedThenDelete() {
        UUID expectedUUID = UUID.randomUUID();
        lendBooksService.deleteByID(expectedUUID.toString());
        verify(lendBooksRepository).deleteById(expectedUUID.toString());
    }

    @Test
    public void findAllLendingWhenFoundedThenReturnList() {
        lendBooksService.findAll();
        verify(lendBooksRepository).findAll();
    }

}