package com.returnorder.managment.componentprocessingservice.service.workflowservice;


import com.returnorder.managment.componentprocessingservice.client.PackagingAndDeliveryClient;
import com.returnorder.managment.componentprocessingservice.dto.PackagingAndDeliveryDTO;
import com.returnorder.managment.componentprocessingservice.dto.ProcessRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@Component("accessoryProcessWorkflowService")
@AllArgsConstructor
public class AccessoryProcessWorkflowImpl implements ProcessWorkflowService {

    @Autowired
    private PackagingAndDeliveryClient packagingAndDeliveryClient;

    @Override
    public PackagingAndDeliveryDTO calculatePackagingAndDeliveryCharge(ProcessRequestDTO processRequestDto) {
        return packagingAndDeliveryClient.getPackagingAndDeliveryCharges(processRequestDto);

    }

    @Override
    public double calculateProcessingCharge() {
        return 300;
    }

    @Override
    public LocalDate calculateDateOfDelivery(){
        return LocalDate.now().plusDays(2L);
    }
}
