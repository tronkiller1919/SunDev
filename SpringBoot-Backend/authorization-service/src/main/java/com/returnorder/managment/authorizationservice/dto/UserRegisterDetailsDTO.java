package com.returnorder.managment.authorizationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserRegisterDetailsDTO {

    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
}
