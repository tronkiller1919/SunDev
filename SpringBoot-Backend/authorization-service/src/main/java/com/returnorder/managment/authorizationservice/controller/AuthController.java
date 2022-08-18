package com.returnorder.managment.authorizationservice.controller;

import com.returnorder.managment.authorizationservice.dto.StringToObjectDTO;
import com.returnorder.managment.authorizationservice.dto.UserRegisterDetailsDTO;
import com.returnorder.managment.authorizationservice.exception.BadCredentialsException;
import com.returnorder.managment.authorizationservice.exception.UserDataAlreadyExistsException;
import com.returnorder.managment.authorizationservice.exception.UsernameNotFoundException;
import com.returnorder.managment.authorizationservice.model.UserModel;
import com.returnorder.managment.authorizationservice.service.UserAuthServiceImpl;
import com.returnorder.managment.authorizationservice.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("/*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserAuthServiceImpl userAuthServiceImpl;

    @Autowired
    JwtUtility jwtUtility;

//      Login endpoint for login and use the app
    @RequestMapping(value ="/login",method= RequestMethod.POST,consumes="application/json",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StringToObjectDTO> authenticateUser(@RequestBody UserModel userModel) throws AuthenticationException {

        try{
                userAuthServiceImpl.authenticateUser(userModel.getUsername(),userModel.getPassword());
        }catch(AuthenticationException e){
            throw new BadCredentialsException("Username and password does not match");
        }catch(NullPointerException e) {
            throw new UsernameNotFoundException("Username not found!!");
        }

        return new ResponseEntity<>(userAuthServiceImpl.jwtTokenResponse(userModel.getUsername()), HttpStatus.OK);
    }

//    For new users, register with username and password
    @RequestMapping(value = "/signup",method= RequestMethod.POST,consumes="application/json",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StringToObjectDTO> registerUser(@RequestBody UserRegisterDetailsDTO userRegisterDetailsDTO) throws RuntimeException{
        try{
            userAuthServiceImpl.registerUser(userRegisterDetailsDTO);
        }catch(RuntimeException e){
            throw new UserDataAlreadyExistsException(e.getMessage());
        }
        return  new ResponseEntity<>(new StringToObjectDTO("Registered"),HttpStatus.CREATED);
    }

}
