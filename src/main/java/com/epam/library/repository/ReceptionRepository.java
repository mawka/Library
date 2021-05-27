package com.epam.library.repository;

import com.epam.library.model.Reception;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionRepository extends MongoRepository<Reception, String> {
}
