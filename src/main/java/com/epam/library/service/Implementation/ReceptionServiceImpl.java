package com.epam.library.service.Implementation;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import com.epam.library.dto.BookQuantityDto;
import com.epam.library.model.Reception;
import com.epam.library.repository.ReceptionRepository;
import com.epam.library.service.ReceptionService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReceptionServiceImpl implements ReceptionService {

    private final ReceptionRepository receptionRepository;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ReceptionServiceImpl(ReceptionRepository receptionRepository, MongoTemplate mongoTemplate) {
        this.receptionRepository = receptionRepository;
        this.mongoTemplate = mongoTemplate;
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
    public List<BookQuantityDto> findBookQuantity() {
        TypedAggregation<BookQuantityDto> agg = newAggregation(BookQuantityDto.class,
                group("idBook").sum("quantity").as("quantity")
        );
        List<BookQuantityDto> listOfBookQuantityDto = mongoTemplate.aggregate(agg, Reception.class, BookQuantityDto.class).getMappedResults();
        return listOfBookQuantityDto;
    }

    @Override
    public int findBookQuantityByBookID(String Id) {
        GroupOperation groupByBook = group("idBook")
                .sum("quantity").as("quantity");
        MatchOperation filterBook = match(new Criteria("idBook").is(new ObjectId(Id)));
        SortOperation sortByBook = sort(Sort.by(Sort.Direction.DESC, "idBook"));

        Aggregation aggregation = newAggregation(
                filterBook, groupByBook, sortByBook);

        List<BookQuantityDto> result = mongoTemplate.aggregate(
                aggregation, Reception.class, BookQuantityDto.class).getMappedResults();
        if (!(result.isEmpty())) {
            return result.get(0).getQuantity();
        } else
            return 0;
    }
}
