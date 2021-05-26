package com.epam.library.converter;

import com.epam.library.dto.BookDto;
import com.epam.library.model.Book;
import org.modelmapper.ModelMapper;

public class BookConverter {

    private static ModelMapper modelMapper = new ModelMapper();

    public static BookDto convertToDto(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

    public static Book convertToEntity(BookDto bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }
}
