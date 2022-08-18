package com.returnorder.managment.componentprocessingservice.service;

import com.returnorder.managment.componentprocessingservice.client.PackagingAndDeliveryClient;
import com.returnorder.managment.componentprocessingservice.dto.PackagingAndDeliveryDTO;
import com.returnorder.managment.componentprocessingservice.dto.ProcessRequestDTO;
import com.returnorder.managment.componentprocessingservice.dto.ProcessResponseDTO;
import com.returnorder.managment.componentprocessingservice.dto.ProcessedDetailsDTO;
import com.returnorder.managment.componentprocessingservice.entity.ProcessRequest;
import com.returnorder.managment.componentprocessingservice.entity.ProcessResponse;
import com.returnorder.managment.componentprocessingservice.exception.CannotPlaceOrderException;
import com.returnorder.managment.componentprocessingservice.mapper.ProcessRequestMapper;
import com.returnorder.managment.componentprocessingservice.mapper.ProcessResponseMapper;
import com.returnorder.managment.componentprocessingservice.mapper.ProcessedDetailsMapper;
import com.returnorder.managment.componentprocessingservice.repository.ProcessRequestRepository;
import com.returnorder.managment.componentprocessingservice.repository.ProcessResponseRepository;
import com.returnorder.managment.componentprocessingservice.service.workflowservice.ProcessWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class ProcessRequestServiceImpl implements ProcessRequestService {


    @Autowired
    PackagingAndDeliveryClient packagingAndDeliveryClient;

    @Qualifier("integralProcessWorkflowService")
    @Autowired
    ProcessWorkflowService integralProcessWorkflowService;

    @Qualifier("accessoryProcessWorkflowService")
    @Autowired
    ProcessWorkflowService accessoryProcessWorkflowService;

    @Autowired
    ProcessResponseRepository processResponseRepository;

    @Autowired
    ProcessRequestRepository processRequestRepository;

    @Autowired
    ProcessedDetailsMapper processedDetailsMapper;

    @Autowired
    ProcessResponseMapper processResponseMapper;

    @Autowired
    ProcessRequestMapper processRequestMapper;

    @Autowired
    ProcessResponseDTO processResponseDto;

    @Autowired
    ProcessedDetailsDTO processedDetailsDTO;

    @Autowired
    ProcessResponse processResponseEntity;

    @Autowired
    ProcessRequest processRequestEntity;

//      Map process request entity to DTO
    @Override
    public ProcessRequestDTO processRequestEntityToProcessRequestDto(ProcessRequest processRequest) {
        processRequestEntity = processRequest;
        return processRequestMapper.processRequestEntityToProcessRequestDto(processRequest);

    }
//      Map process response DTO to entity
    @Override
    public ProcessResponse processResponseDtoToProcessResponseEntity(ProcessResponseDTO processResponseDTO) {

        processResponseEntity = processResponseMapper.processResponseDtoToProcessResponseEntity(processResponseDTO);
        return processResponseEntity;

    }
//      Map process response entity to DTO and set message to confirm order
    @Override
    public ProcessedDetailsDTO processResponseEntityToProcessedDetailsDto(ProcessRequest processRequest, ProcessResponse processResponse) {

        processedDetailsDTO = processedDetailsMapper.processResponseEntityToProcessedDetailsDto(processRequest, processResponse);
//        processedDetailsDTO.setMessage("Confirm your order");
        return processedDetailsDTO;

    }
//      Get packaging and delivery charge from other microservice
    @Override
    public PackagingAndDeliveryDTO getPackagingAndDeliveryCharge(ProcessRequestDTO processRequestDto) {

        return packagingAndDeliveryClient.getPackagingAndDeliveryCharges(processRequestDto);
    }

//      Process required calculations and call necessary workflow based on component type
    @Override
    public ProcessResponseDTO processComponentDetails(ProcessRequestDTO processRequestDto) {

        String componentType = processRequestDto.getComponentType();

//        ProcessResponseDTO processResponseDto;


        double packagingAndDeliveryCharge = 0;
        double processingCharge = 0;
        LocalDate dateOfDelivery = null;

        if (componentType.equalsIgnoreCase("integral")) {
            packagingAndDeliveryCharge = integralProcessWorkflowService.calculatePackagingAndDeliveryCharge(processRequestDto).getPackagingAndDeliveryCharge();
            processingCharge = integralProcessWorkflowService.calculateProcessingCharge();
            dateOfDelivery = integralProcessWorkflowService.calculateDateOfDelivery();
        }

        if (componentType.equalsIgnoreCase("Accessory")) {
            packagingAndDeliveryCharge = accessoryProcessWorkflowService.calculatePackagingAndDeliveryCharge(processRequestDto).getPackagingAndDeliveryCharge();
            processingCharge = accessoryProcessWorkflowService.calculateProcessingCharge();
            dateOfDelivery = accessoryProcessWorkflowService.calculateDateOfDelivery();
        }

        processResponseDto = new ProcessResponseDTO(packagingAndDeliveryCharge, processingCharge, dateOfDelivery);

        return processResponseDto;

    }
//      Upon user confirmation save the data into the H2 database
    @Override
    public void saveOrderDetailsToDatabase() {

//        Get response id from the entity to avoid repeated request of same instance
        String id = processResponseEntity.getResponseId() == null ? "0" : processResponseEntity.getResponseId();

//       Check if request contains same instance data if incoming data is a new instance save details to the database and set order placed flag to true
        if (processRequestEntity.getCustomerName() != null && !processResponseRepository.existsProcessResponseByResponseId(id)) {
            processResponseEntity.setProcessRequest(processRequestEntity);
            processResponseRepository.save(processResponseEntity);
            processedDetailsDTO.setMessage("Order has been placed successfully!! Sit back and relax we'll do the rest");
        }

//        If same instance data is sent as a request notify users that order is placed and to check order history
        else if (processResponseRepository.existsProcessResponseByResponseId(id)) {
            processedDetailsDTO.setMessage("Your order was placed successfully!!! Your order no: " + id + " Track your order in order history.");
        }

//        If current instance is null or no instance is created we cannot place order. Throw exception with a message
        else {
            throw new CannotPlaceOrderException("Cannot Place Order!!!");
        }

    }
//      Display the processed order data to the user
    @Override
    public ProcessedDetailsDTO displayProcessedDetails() {

        return processedDetailsDTO;
    }

}


