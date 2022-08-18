package com.returnorder.managment.componentprocessingservice.mapper;

import com.returnorder.managment.componentprocessingservice.dto.ProcessedDetailsDTO;
import com.returnorder.managment.componentprocessingservice.entity.ProcessRequest;
import com.returnorder.managment.componentprocessingservice.entity.ProcessResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProcessedDetailsMapper {

    ProcessedDetailsMapper INSTANCE = Mappers.getMapper(ProcessedDetailsMapper.class);

    @Mapping(source="processRequest.customerName",target="customerName")
    @Mapping(source="processRequest.contactNumber",target="customerNumber")
    @Mapping(source="processRequest.componentType",target="componentType")
    @Mapping(source="processRequest.componentName",target="componentName")
    @Mapping(source="processRequest.quantity",target="quantity")
    ProcessedDetailsDTO processResponseEntityToProcessedDetailsDto(ProcessRequest processRequest, ProcessResponse processResponse);

}
