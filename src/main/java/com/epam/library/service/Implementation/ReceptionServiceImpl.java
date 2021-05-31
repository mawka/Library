package com.epam.library.service.Implementation;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import com.epam.library.model.Reception;
import com.epam.library.repository.ReceptionRepository;
import com.epam.library.service.ReceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReceptionServiceImpl implements ReceptionService {

    private final ReceptionRepository receptionRepository;

    @Autowired
    public ReceptionServiceImpl(ReceptionRepository receptionRepository) {
        this.receptionRepository = receptionRepository;
    }

    @Override
    public void deleteByID(String id) {
        receptionRepository.deleteById(id);
    }

    @Override
    public List<Reception> findAll() {
        return receptionRepository.findAll();
    }

    @Override
    public Reception findByID(String Id) {
        return receptionRepository.findById(Id).orElseThrow(() -> new RuntimeException("This record doesn't exist!"));
    }

    @Override
    public Reception save(Reception reception) {
        return receptionRepository.save(reception);
    }

    @Override
    public void update(Reception reception) {
        receptionRepository.save(reception);
    }

    @Override
    public List<Reception> findBookQuantity() {
//        Aggregation aggregation = Aggregation.newAggregation(
//                group("idBook").sum("quantity").as("Books")
//        );
//
////        AggregationResults<Yorum> results = .aggregate(aggregation, Yorum.class);
////
////        System.out.println(results.getRawResults().get("result"));
////
////        return results.getRawResults().get("result").toString();
//
//        return (List<Reception>) aggregation;


        return receptionRepository.findBookQuantity();


    }
}
