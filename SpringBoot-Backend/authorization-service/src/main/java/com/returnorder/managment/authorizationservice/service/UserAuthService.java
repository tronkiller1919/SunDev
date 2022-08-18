package com.returnorder.managment.authorizationservice.service;

import com.returnorder.managment.authorizationservice.dto.StringToObjectDTO;
import com.returnorder.managment.authorizationservice.dto.UserRegisterDetailsDTO;

public interface UserAuthService {
     void authenticateUser(String username,String password);
     StringToObjectDTO jwtTokenResponse(String username);
     void registerUser(UserRegisterDetailsDTO userRegisterDetailsDTO);

}
