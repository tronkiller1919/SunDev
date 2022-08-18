package com.returnorder.managment.componentprocessingservice.client;

import com.returnorder.managment.componentprocessingservice.dto.PackagingAndDeliveryDTO;
import com.returnorder.managment.componentprocessingservice.dto.ProcessRequestDTO;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "packaging-and-delivery-service")
public interface PackagingAndDeliveryClient {

    @RequestMapping("/process-order/packaging-delivery-charge")
    PackagingAndDeliveryDTO getPackagingAndDeliveryCharges(@RequestBody ProcessRequestDTO processRequestDto) throws FeignException;

    @RequestMapping("/process-order/test")
    String testConnection();
}
