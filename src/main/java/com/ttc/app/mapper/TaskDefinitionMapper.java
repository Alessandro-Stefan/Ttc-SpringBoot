package com.ttc.app.mapper;

import java.time.LocalDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ttc.app.dto.taskDefinition.AddTaskDefinitionRequest;
import com.ttc.app.dto.taskDefinition.TaskDefinitionDto;

@Mapper(componentModel = "spring")
public interface TaskDefinitionMapper {

    @Mapping(target = "userId", source = "user.id")
    TaskDefinitionDto toDto (com.ttc.app.entity.TaskDefinitionEntity model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", expression = "java(toDateNow())")
    @Mapping(target = "updatedAt", ignore = true)
    com.ttc.app.entity.TaskDefinitionEntity toEntity (AddTaskDefinitionRequest dto);

    default LocalDateTime toDateNow() {
        return LocalDateTime.now();
    }
}
