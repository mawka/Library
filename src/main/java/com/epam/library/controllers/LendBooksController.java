package com.epam.library.controllers;

import com.epam.library.converter.LendBooksConverter;
import com.epam.library.dto.LendBooksDto;
import com.epam.library.model.LendBooks;
import com.epam.library.service.Implementation.LendBooksServiceImpl;
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
@RequestMapping("/lending")
public class LendBooksController {

    private LendBooksServiceImpl lendBooksService;

    @Autowired
    private LendBooksController(LendBooksServiceImpl lendBooksService) {
        this.lendBooksService = lendBooksService;
    }

    @GetMapping
    public List<LendBooksDto> getAllLending() {
        List<LendBooks> lendBooks = lendBooksService.findAll();
        return lendBooks.stream()
                .map(LendBooksConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public LendBooksDto getLending(@PathVariable("id") String id) {
        return LendBooksConverter.convertToDto(lendBooksService.findByID(id));
    }

    @PostMapping
    public LendBooksDto createLending(@RequestBody @Valid LendBooksDto lendBooksDto) {
        LendBooks lendBooks = LendBooksConverter.convertToEntity(lendBooksDto);
        lendBooksService.save(lendBooks);
        return LendBooksConverter.convertToDto(lendBooks);
    }

    @PutMapping
    public void updateLending(@RequestBody @Valid LendBooksDto lendBooksDto) {
        LendBooks lendBooks = LendBooksConverter.convertToEntity(lendBooksDto);
        lendBooksService.save(lendBooks);
    }

    @DeleteMapping("/{id}")
    public void deleteLending(@PathVariable("id") String id) {
        lendBooksService.deleteByID(id);
    }
}
