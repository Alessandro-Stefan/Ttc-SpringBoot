package com.ttc.app.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.ttc.app.dto.task.AddTaskRequest;
import com.ttc.app.dto.task.TaskDto;
import com.ttc.app.entity.TaskDefinitionEntity;
import com.ttc.app.entity.TaskEntity;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "definitionId", source = "taskDefinition.id")
    @Mapping(target = "createdAt", expression = "java(dateToString(entity.getCreatedAt()))")
    @Mapping(target = "updatedAt", expression = "java(dateToString(entity.getUpdatedAt()))")
    TaskDto toDto(com.ttc.app.entity.TaskEntity entity);

    List<TaskDto> toDtoList(List<TaskEntity> entities);

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

    default String dateToString(LocalDateTime date) {
        if (date != null)
            return date.toString();

        return null;
    }
}
