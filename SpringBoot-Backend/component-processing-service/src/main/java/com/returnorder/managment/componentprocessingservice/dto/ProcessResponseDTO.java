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
public class ProcessResponseDTO {

    private double packagingAndDeliveryCharge;
    private double processingCharge;
    private LocalDate dateOfDelivery;

}
