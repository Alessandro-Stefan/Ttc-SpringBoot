package com.ttc.app.mapper;

import java.time.LocalDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ttc.app.dto.task.AddTaskRequest;
import com.ttc.app.dto.task.TaskDto;
import com.ttc.app.entity.TaskDefinitionEntity;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "definitionId", source = "taskDefinition.id")
    TaskDto toDto(com.ttc.app.entity.TaskEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "taskDefinition", expression = "java(toTaskDefinition(dto.definitionId()))")
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", expression = "java(toDateNow())")
    com.ttc.app.entity.TaskEntity toEntity(AddTaskRequest dto);

    default TaskDefinitionEntity toTaskDefinition(Long id) {
        TaskDefinitionEntity td = new TaskDefinitionEntity();
        td.setId(id);
        return td;
    }

    default LocalDateTime toDateNow() {
        return LocalDateTime.now();
    }
}
