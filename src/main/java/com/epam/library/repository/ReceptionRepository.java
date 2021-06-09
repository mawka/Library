package com.epam.library.repository;

import com.epam.library.dto.BookQuantityDto;
import com.epam.library.model.Reception;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceptionRepository extends MongoRepository<Reception, String> {

    @Query("{}")
    List<BookQuantityDto> findBookQuantity();

    @Query("{}")
    Integer findBookQuantityByBookID(String id);
}
