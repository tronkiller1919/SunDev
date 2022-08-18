package com.returnorder.managment.packaginganddeliveryservice.service;

import com.returnorder.managment.packaginganddeliveryservice.dto.ProcessRequestDto;
import com.returnorder.managment.packaginganddeliveryservice.dto.ProcessResponseDto;
import com.returnorder.managment.packaginganddeliveryservice.exception.ComponentNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PackagingAndDeliveryServiceImpl implements PackagingAndDeliveryService{

    @Override
    public ProcessResponseDto calculateOrderCharges(ProcessRequestDto processRequestDto) {

        double packagingCharge;
        double deliveryCharge;
        Integer protectiveSheath = 50;
        String componentType = processRequestDto.getComponentType();
        Integer quantity = processRequestDto.getQuantity();


        if(componentType.equalsIgnoreCase("integral")) {

            packagingCharge =  this.calculateCharges(quantity,(100+protectiveSheath));
            deliveryCharge = 200;

        }

        else if(componentType.equalsIgnoreCase("accessory")) {
            packagingCharge = this.calculateCharges(quantity,(50+protectiveSheath));
            deliveryCharge = 100;
        }
        else{
                throw new ComponentNotFoundException("Component "+componentType+" not found!!");
        }

        return new ProcessResponseDto(packagingCharge + deliveryCharge);
    }


    @Override
    public double calculateCharges( Integer quantity, double price) {
        return quantity * price;
    }


}
