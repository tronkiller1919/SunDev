package com.returnorder.managment.componentprocessingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class ProcessedDetailsDTO {

//    private String responseId;
    private String customerName;
    private String customerNumber;
    private String componentType;
    private String componentName;
    private Integer quantity;
    private double processingCharge;
    private double packagingAndDeliveryCharge;
    private LocalDate dateOfDelivery;
    private String message;

}
