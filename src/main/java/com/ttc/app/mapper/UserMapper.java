package com.ttc.app.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ttc.app.dto.user.AddUserRequest;
import com.ttc.app.dto.user.UserDto;
import com.ttc.app.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "createdAt", expression = "java(datetoString(model.getCreatedAt()))")
    @Mapping(target = "updatedAt", expression = "java(datetoString(model.getUpdatedAt()))")
    UserDto toDto(com.ttc.app.entity.UserEntity model);

    List<UserDto> toDtos(List<UserEntity> models);

    //Ignore espiliciti in modo che nella conversione il mapper non sovrascriva in null i campi in questione del modello
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserEntity toEntity(AddUserRequest dto);

    default String datetoString(LocalDateTime date) {
        if (date != null)
            return date.toString();

        return null;
    }
}
