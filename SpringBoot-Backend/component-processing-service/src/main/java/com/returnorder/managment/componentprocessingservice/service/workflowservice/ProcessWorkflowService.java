package com.returnorder.managment.componentprocessingservice.service.workflowservice;

import com.returnorder.managment.componentprocessingservice.dto.PackagingAndDeliveryDTO;
import com.returnorder.managment.componentprocessingservice.dto.ProcessRequestDTO;

import java.time.LocalDate;

public interface ProcessWorkflowService {

    PackagingAndDeliveryDTO calculatePackagingAndDeliveryCharge(ProcessRequestDTO processRequestDto);

    double calculateProcessingCharge();

    LocalDate calculateDateOfDelivery();


}
