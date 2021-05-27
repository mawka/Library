package com.epam.library.controllers;

import static com.epam.library.converter.ReceptionConverter.convertToDto;
import static com.epam.library.converter.ReceptionConverter.convertToEntity;
import com.epam.library.converter.ReceptionConverter;
import com.epam.library.dto.ReceptionDto;
import com.epam.library.model.Reception;
import com.epam.library.service.Implementation.ReceptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reception")
public class ReceptionController {

    private ReceptionServiceImpl receptionService;

    @Autowired
    private ReceptionController(ReceptionServiceImpl receptionService) {
        this.receptionService = receptionService;
    }


    @GetMapping
    public List<ReceptionDto> getAllReception() {
        List<Reception> reception = receptionService.findAll();
        return reception.stream()
                .map(ReceptionConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/quantity")
    public List<ReceptionDto> getAllBookQuantity() {
        List<Reception> reception = receptionService.findBookQuantity();
        return reception.stream()
                .map(ReceptionConverter::convertToDto)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ReceptionDto getReception(@PathVariable("id") String id) {
        return convertToDto(receptionService.findByID(id));
    }

    @PostMapping
    public ReceptionDto createReception(@RequestBody @Valid ReceptionDto receptionDto) {
        Reception reception = convertToEntity(receptionDto);
        Reception receptionCreated = receptionService.save(reception);
        return convertToDto(receptionCreated);
    }

    @PutMapping
    public void updateReception(@RequestBody @Valid ReceptionDto receptionDto) {
        Reception reception = convertToEntity(receptionDto);
        receptionService.save(reception);
    }

    @DeleteMapping("/{id}")
    public void deleteReception(@PathVariable("id") String id) {
        receptionService.deleteByID(id);
    }

}
