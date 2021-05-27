package com.epam.library.listener;

import com.epam.library.config.RabbitConfiguration;
import com.epam.library.model.LendBooks;
import com.epam.library.repository.LendBooksRepository;
import com.epam.library.service.Implementation.LendBooksServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {

    private final LendBooksRepository lendBooksRepository;

    Logger logger = LoggerFactory.getLogger(LendBooksServiceImpl.class);

    @Autowired
    public RabbitMqListener(LendBooksRepository lendBooksRepository) {
        this.lendBooksRepository = lendBooksRepository;
    }

    @RabbitListener(queues = RabbitConfiguration.QUEUE)
    public void getMessageFromQueueAndSave(LendBooks lendBooks) {
        logger.info("Receive from lending_queue");
        lendBooksRepository.save(lendBooks);
    }
}
