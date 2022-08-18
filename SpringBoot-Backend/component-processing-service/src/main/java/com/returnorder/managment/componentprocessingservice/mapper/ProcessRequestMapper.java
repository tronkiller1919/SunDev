package com.returnorder.managment.componentprocessingservice.mapper;


import com.returnorder.managment.componentprocessingservice.dto.ProcessRequestDTO;
import com.returnorder.managment.componentprocessingservice.entity.ProcessRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface ProcessRequestMapper {

    ProcessRequestMapper INSTANCE = Mappers.getMapper(ProcessRequestMapper.class);

    ProcessRequestDTO processRequestEntityToProcessRequestDto(ProcessRequest processRequest);

}
