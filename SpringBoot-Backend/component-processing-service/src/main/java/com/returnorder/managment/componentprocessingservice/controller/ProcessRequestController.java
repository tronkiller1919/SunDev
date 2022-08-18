package com.returnorder.managment.componentprocessingservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.returnorder.managment.componentprocessingservice.dto.PackagingAndDeliveryDTO;
import com.returnorder.managment.componentprocessingservice.dto.ProcessRequestDTO;
import com.returnorder.managment.componentprocessingservice.dto.ProcessResponseDTO;
import com.returnorder.managment.componentprocessingservice.entity.ProcessRequest;
import com.returnorder.managment.componentprocessingservice.entity.ProcessResponse;
import com.returnorder.managment.componentprocessingservice.exception.CannotPlaceOrderException;
import com.returnorder.managment.componentprocessingservice.exception.FeignClientException;
import com.returnorder.managment.componentprocessingservice.service.ProcessRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/component-process")
public class ProcessRequestController {

    @Autowired
    ProcessRequestService processRequestService;

    @Autowired
    ObjectMapper objectMapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

//    Home page to get component details from the user and display the order details for user confirmation
    @RequestMapping(value="/process-request",method = RequestMethod.GET)
    public ResponseEntity<?> processComponentOrder(@RequestParam(value = "processRequest") ProcessRequest processRequest) {
//          Convert Process Request object into Process request DTO
        ProcessRequestDTO processRequestDto = processRequestService.processRequestEntityToProcessRequestDto(processRequest);

//          get the calculated packaging and delivery charges from PackagingAndDelivery microservice
        try{
            PackagingAndDeliveryDTO packagingAndDeliveryDto = processRequestService.getPackagingAndDeliveryCharge(processRequestDto);
        }
        catch(RuntimeException e){
            throw new FeignClientException(e.getMessage());
        }

//          Function to process the component details from Process Request DTO object
        ProcessResponseDTO processResponseDto = processRequestService.processComponentDetails(processRequestDto);

//          Convert Process response DTO to Process response Entity
        ProcessResponse processResponse = processRequestService.processResponseDtoToProcessResponseEntity(processResponseDto);

//          return mapped ProcessedResponse to user
        return ResponseEntity.ok(processRequestService.processResponseEntityToProcessedDetailsDto(processRequest, processResponse));
    }

//      Upon user confirmation store the order details into the database and display the confirmed order details to the user
    @RequestMapping(value = "/complete-processing", method = RequestMethod.POST)
    public ResponseEntity<?> completeProcessing() {

        try{
//          Save order details to database
        processRequestService.saveOrderDetailsToDatabase();
        }catch(RuntimeException e){
            throw new CannotPlaceOrderException(e.getMessage());
        }

//          return order details with success message
        return ResponseEntity.ok(processRequestService.displayProcessedDetails());
    }

    @RequestMapping(value = "/testing",method=RequestMethod.GET)
    public ResponseEntity<?> testing(@RequestParam(value = "processRequestTest") String processRequestTest){

        return ResponseEntity.ok(processRequestTest);
    }

}
