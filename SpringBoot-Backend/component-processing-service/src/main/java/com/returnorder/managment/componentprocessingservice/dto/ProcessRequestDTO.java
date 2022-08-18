package com.returnorder.managment.componentprocessingservice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProcessRequestDTO {

    private String componentType;
    private Integer quantity;

}
