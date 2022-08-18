package com.returnorder.managment.componentprocessingservice.service;

import com.returnorder.managment.componentprocessingservice.dto.*;
import com.returnorder.managment.componentprocessingservice.entity.ProcessRequest;
import com.returnorder.managment.componentprocessingservice.entity.ProcessResponse;

import java.time.LocalDate;

public interface ProcessRequestService {

    //  Function to convert Process Request Entity to DTO
    ProcessRequestDTO processRequestEntityToProcessRequestDto(ProcessRequest processRequest);

    //  Function to convert Process Response DTO to Entity
    ProcessResponse processResponseDtoToProcessResponseEntity(ProcessResponseDTO processResponseDto);

    //  Function to convert Processed details to DTO
    ProcessedDetailsDTO processResponseEntityToProcessedDetailsDto(ProcessRequest processRequest,ProcessResponse processResponse);

    //  Function to calculate the packaging and delivery charge from PackagingAndDelivery microservice
    PackagingAndDeliveryDTO getPackagingAndDeliveryCharge(ProcessRequestDTO processRequestDto);

    //  Function to get the details of the component details like processing charge, date of delivery, packaging and delivery charge
    ProcessResponseDTO processComponentDetails(ProcessRequestDTO processRequestDto);

    //  save the process response and process request to H2 database
    void saveOrderDetailsToDatabase();

    //  display order details
    ProcessedDetailsDTO displayProcessedDetails();

}
