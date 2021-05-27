package com.epam.library.converter;

import com.epam.library.dto.LendBooksDto;
import com.epam.library.model.LendBooks;
import org.modelmapper.ModelMapper;

public class LendBooksConverter {
    private static ModelMapper modelMapper = new ModelMapper();

    public static LendBooksDto convertToDto(LendBooks lendBooks) {
        return modelMapper.map(lendBooks, LendBooksDto.class);
    }

    public static LendBooks convertToEntity(LendBooksDto lendBooksDto) {
        return modelMapper.map(lendBooksDto, LendBooks.class);
    }

}
