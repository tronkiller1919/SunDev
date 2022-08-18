package com.returnorder.managment.componentprocessingservice.mapper;

import com.returnorder.managment.componentprocessingservice.dto.ProcessResponseDTO;
import com.returnorder.managment.componentprocessingservice.entity.ProcessResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface ProcessResponseMapper {

    ProcessResponseMapper INSTANCE = Mappers.getMapper(ProcessResponseMapper.class);

    @Mapping(target="responseId",ignore=true)
    @Mapping(target="processRequest",ignore=true)
    ProcessResponse processResponseDtoToProcessResponseEntity(ProcessResponseDTO processResponseDto);

}
