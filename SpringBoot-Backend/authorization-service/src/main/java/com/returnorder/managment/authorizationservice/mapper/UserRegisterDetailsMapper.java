package com.returnorder.managment.authorizationservice.mapper;

import com.returnorder.managment.authorizationservice.dto.UserRegisterDetailsDTO;
import com.returnorder.managment.authorizationservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface UserRegisterDetailsMapper {

    UserRegisterDetailsMapper INSTANCE = Mappers.getMapper(UserRegisterDetailsMapper.class);

    @Mapping(target="id",ignore=true)
    User userRegisterDetailsDtoToUserEntity(UserRegisterDetailsDTO userRegisterDetailsDTO);
}
