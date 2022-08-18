package com.returnorder.managment.packaginganddeliveryservice.controller;


import com.returnorder.managment.packaginganddeliveryservice.dto.ProcessRequestDto;
import com.returnorder.managment.packaginganddeliveryservice.dto.ProcessResponseDto;
import com.returnorder.managment.packaginganddeliveryservice.service.PackagingAndDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process-order")
public class PackagingAndDeliveryController {

    @Autowired
    PackagingAndDeliveryService packagingAndDeliveryService;


    @RequestMapping("/packaging-delivery-charge")
    public ProcessResponseDto calculateOrderCharges(@RequestBody ProcessRequestDto processRequestDto){

        return  packagingAndDeliveryService.calculateOrderCharges(processRequestDto);
    }

    @GetMapping("/package-test")
    public ProcessResponseDto packageTest(@RequestBody ProcessRequestDto processRequestDto){
        return packagingAndDeliveryService.calculateOrderCharges(processRequestDto);
    }
    @GetMapping("/test")
    public String testConnection(){
        return "This text is returned from package service";
    }
}
