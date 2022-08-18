package com.returnorder.managment.packaginganddeliveryservice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProcessRequestDto {

    private String componentType;
    private Integer quantity;

}
