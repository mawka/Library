package com.epam.library.repository;

import com.epam.library.model.Reception;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReceptionRepository extends MongoRepository<Reception, String> {
}
