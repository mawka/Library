package com.epam.library.converter;

import com.epam.library.dto.ReceptionDto;
import com.epam.library.model.Reception;
import org.modelmapper.ModelMapper;

public class ReceptionCoverter {
    private static ModelMapper modelMapper = new ModelMapper();

    public static ReceptionDto convertToDto(Reception reception) {
        return modelMapper.map(reception, ReceptionDto.class);
    }

    public static Reception convertToEntity(ReceptionDto receptionDto) {
        return modelMapper.map(receptionDto, Reception.class);
    }
}