package com.epam.library.repository;

import com.epam.library.model.Reception;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceptionRepository extends MongoRepository<Reception, String> {
    @Query("{aggregate( [ {$group :{ _id : \"Book\", total_quantity: { $sum : \"$quantity\" }}} ] )}")
    List<Reception> findBookQuantity();
}
