package com.ttc.app.mapper;

import org.mapstruct.Mapper;

import com.ttc.app.dto.taskDefinition.TaskDefinitionDto;

@Mapper(componentModel = "spring")
public interface TaskDefinitionMapper {

    TaskDefinitionDto toDto (com.ttc.app.entity.TaskDefinitionEntity model);

    com.ttc.app.entity.TaskDefinitionEntity toEntity (TaskDefinitionDto dto);
}
