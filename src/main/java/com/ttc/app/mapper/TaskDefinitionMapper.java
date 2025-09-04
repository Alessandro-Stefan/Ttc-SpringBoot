package com.ttc.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ttc.app.dto.taskDefinition.AddTaskDefinitionRequest;
import com.ttc.app.dto.taskDefinition.TaskDefinitionDto;

@Mapper(componentModel = "spring")
public interface TaskDefinitionMapper {

    TaskDefinitionDto toDto (com.ttc.app.entity.TaskDefinitionEntity model);

    @Mapping(target = "id", ignore = true)
    com.ttc.app.entity.TaskDefinitionEntity toEntity (AddTaskDefinitionRequest dto);
}
