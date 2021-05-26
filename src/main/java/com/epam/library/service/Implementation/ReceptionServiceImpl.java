package com.epam.library.service.Implementation;

import com.epam.library.model.Reception;
import com.epam.library.repository.ReceptionRepository;
import com.epam.library.service.ReceptionService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
