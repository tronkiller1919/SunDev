package com.returnorder.managment.componentprocessingservice.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.returnorder.managment.componentprocessingservice.entity.ProcessRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProcessRequestStringToJsonConverter implements Converter<String, ProcessRequest> {

    ObjectMapper mapper = new ObjectMapper();
    @Override
    public ProcessRequest convert(String source) {
        try{
            return mapper.readValue(source,ProcessRequest.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
