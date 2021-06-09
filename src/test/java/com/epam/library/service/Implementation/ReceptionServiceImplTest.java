package com.epam.library.service.Implementation;

import static java.util.Optional.of;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import com.epam.library.LibraryApplication;
import com.epam.library.dto.BookQuantityDto;
import com.epam.library.model.Reception;
import com.epam.library.repository.ReceptionRepository;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class ReceptionServiceImplTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock
    private ReceptionRepository receptionRepository;

    @Mock
    private MongoTemplate mongoTemplate;

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

    @Ignore
    @Test
    public void findBookQuantityWhenFindThenReturnListOfBooks() {
        List<BookQuantityDto> expectedList = new ArrayList<>();
        expectedList.add(new BookQuantityDto());
        when(receptionService.findBookQuantity()).thenReturn(expectedList);
        List<BookQuantityDto> actualList = receptionService.findBookQuantity();
        assertEquals(expectedList, actualList);
    }

    @Test
    public void findAllReceptionWhenFoundedThenReturnList() {
        receptionService.findAll();
        verify(receptionRepository).findAll();
    }

    @Ignore
    @Test
    public void findBookQuantityWhenDataIsPresentThenReturnList() {
        TypedAggregation<BookQuantityDto> agg = newAggregation(BookQuantityDto.class,
                group("idBook").sum("quantity").as("quantity")
        );
        assertThat(agg, is(notNullValue()));
        assertThat(agg.toString(), is(notNullValue()));
        AggregationResults<BookQuantityDto> result = mongoTemplate.aggregate(agg, Reception.class, BookQuantityDto.class);
        assertThat(result, is(notNullValue()));
        assertThat(result.getMappedResults(), is(notNullValue()));

        BookQuantityDto actualBookQuantity = result.getMappedResults().get(0);
        assertThat(actualBookQuantity, is(notNullValue()));
    }
}