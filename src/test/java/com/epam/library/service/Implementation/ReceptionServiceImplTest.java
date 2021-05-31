package com.epam.library.service.Implementation;

import static java.util.Optional.of;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.epam.library.LibraryApplication;
import com.epam.library.model.Book;
import com.epam.library.model.Reception;
import com.epam.library.repository.ReceptionRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class ReceptionServiceImplTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock
    private ReceptionRepository receptionRepository;

    @InjectMocks
    private ReceptionServiceImpl receptionService;

    @Test
    public void findByIDWhenReceptionIsFoundedThenReturnReception() {
        UUID actualUUID = UUID.randomUUID();
        Reception expectedReception = new Reception();
        expectedReception.setId(actualUUID.toString());
        when(receptionRepository.findById(actualUUID.toString()))
                .thenReturn(of(of(expectedReception)).get());
        Reception actualReception = receptionService.findByID(actualUUID.toString());
        assertSame(expectedReception, actualReception);
    }

    @Test
    public void findByIDWhenReceptionItsNotFoundedThenThrowException() {
        UUID UUIDExpected = UUID.randomUUID();

        String expectedException = "This record doesn't exist!";
        exception.expect(RuntimeException.class);
        exception.expectMessage(expectedException);
        receptionService.findByID(UUIDExpected.toString());
    }

    @Test
    public void saveReceptionWhenSaveThenReturnReception() {
        Reception expectedReception = new Reception();
        UUID expectedUUID = UUID.randomUUID();
        expectedReception.setId(expectedUUID.toString());
        when(receptionRepository.save(expectedReception)).thenReturn(expectedReception);
        Reception actualReception = receptionService.save(expectedReception);
        assertSame(expectedReception, actualReception);
    }

    @Test
    public void updateReceptionWhenUpdateReceptionThenSave(){
        Reception expectedReception = new Reception();
        UUID expectedUUID = UUID.randomUUID();
        expectedReception.setId(expectedUUID.toString());
        when(receptionRepository.save(expectedReception)).thenReturn(expectedReception);
        receptionService.update(expectedReception);
        when(receptionRepository.findById(expectedUUID.toString())).thenReturn(of(expectedReception));
        assertSame(expectedReception,receptionRepository.findById(expectedUUID.toString()).get());
    }


    @Test
    public void deleteByIDWhenStudentFoundedThenDelete() {
        UUID expectedUUID = UUID.randomUUID();
        receptionService.deleteByID(expectedUUID.toString());
        verify(receptionRepository).deleteById(expectedUUID.toString());
    }
}