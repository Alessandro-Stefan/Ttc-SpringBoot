package com.ttc.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ttc.app.dto.taskDefinition.AddTaskDefinitionRequest;
import com.ttc.app.dto.taskDefinition.TaskDefinitionDto;
import com.ttc.app.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface TaskDefinitionMapper {

    @Mapping(target = "userId", source = "user.id")
    TaskDefinitionDto toDto (com.ttc.app.entity.TaskDefinitionEntity model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    com.ttc.app.entity.TaskDefinitionEntity toEntity (AddTaskDefinitionRequest dto);
}
