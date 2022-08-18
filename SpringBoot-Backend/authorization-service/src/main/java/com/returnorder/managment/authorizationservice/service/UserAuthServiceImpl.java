package com.returnorder.managment.authorizationservice.service;

import com.returnorder.managment.authorizationservice.dto.StringToObjectDTO;
import com.returnorder.managment.authorizationservice.dto.UserRegisterDetailsDTO;
import com.returnorder.managment.authorizationservice.entity.User;
import com.returnorder.managment.authorizationservice.exception.UserDataAlreadyExistsException;
import com.returnorder.managment.authorizationservice.mapper.UserRegisterDetailsMapper;
import com.returnorder.managment.authorizationservice.repository.UsersRepository;
import com.returnorder.managment.authorizationservice.utility.JwtUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    JwtUtility jwtUtility;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRegisterDetailsMapper userRegisterDetailsMapper;

    @Autowired
    UsersRepository usersRepository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

//      authenticate user with username and password
    @Override
    public void authenticateUser(String username, String password) throws RuntimeException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password,new ArrayList<>()));
    }
//      return the jwt token for the user
    @Override
    public StringToObjectDTO jwtTokenResponse(String username) {
        return new StringToObjectDTO(jwtUtility.generateToken(username));
    }
//      get new user details from the client and register the user
    @Override
    public void registerUser(UserRegisterDetailsDTO userRegisterDetailsDTO) throws RuntimeException{

            User userEntity = userRegisterDetailsMapper.userRegisterDetailsDtoToUserEntity(userRegisterDetailsDTO);
            if(usersRepository.existsUserByUsername(userEntity.getUsername()))
                throw new UserDataAlreadyExistsException("Username already exists!!");

            if(usersRepository.existsUserByEmail(userEntity.getEmail()))
                throw new UserDataAlreadyExistsException("Email already registered!!");
            usersRepository.save(userEntity);
    }

}
