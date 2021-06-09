package com.epam.library.service.Implementation;

import com.epam.library.config.RabbitConfiguration;
import com.epam.library.model.LendBooks;
import com.epam.library.repository.LendBooksRepository;
import com.epam.library.service.LendBooksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LendBooksServiceImpl implements LendBooksService {

    private final LendBooksRepository lendBooksRepository;

    private final ReceptionServiceImpl receptionService;

    private final RabbitTemplate rabbitTemplate;


    @Autowired
    public LendBooksServiceImpl(LendBooksRepository lendBooksRepository, ReceptionServiceImpl receptionService, RabbitTemplate rabbitTemplate) {
        this.lendBooksRepository = lendBooksRepository;
        this.receptionService = receptionService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void deleteByID(String id) {
        lendBooksRepository.deleteById(id);
    }

    @Override
    public List<LendBooks> findAll() {
        return lendBooksRepository.findAll();
    }

    @Override
    public LendBooks findByID(String Id) {
        return lendBooksRepository.findById(Id).orElseThrow(() -> new RuntimeException("Operation with this id not founded!"));
    }

    @Override
    public void save(LendBooks lendBooks) {
        if (receptionService.findBookQuantityByBookID(lendBooks.getIdBook()) > 0) {
            rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE, RabbitConfiguration.ROUTING_KEY, lendBooks);
        } else
            throw new RuntimeException("Book quantity doesnt enough for lending!");
    }

    @Override
    public void update(LendBooks lendBooks) {
        lendBooksRepository.save(lendBooks);
    }
}
