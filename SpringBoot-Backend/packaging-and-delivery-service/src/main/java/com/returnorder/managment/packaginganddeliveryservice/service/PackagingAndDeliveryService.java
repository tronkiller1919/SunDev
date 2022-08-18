package com.returnorder.managment.packaginganddeliveryservice.service;

import com.returnorder.managment.packaginganddeliveryservice.dto.ProcessRequestDto;
import com.returnorder.managment.packaginganddeliveryservice.dto.ProcessResponseDto;
import org.springframework.http.ResponseEntity;

public interface PackagingAndDeliveryService {

    ProcessResponseDto calculateOrderCharges(ProcessRequestDto processRequestDto);

    double calculateCharges(Integer quantity,double price);
}
