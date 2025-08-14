package com.ttc.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ttc.app.dto.user.UserDto;
import com.ttc.app.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(com.ttc.app.entity.UserEntity model);

    //Ignore espiliciti in modo che nella conversione il mapper non sovrascriva in null i campi in questione del modello
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserEntity toEntity(UserDto dto);
}
