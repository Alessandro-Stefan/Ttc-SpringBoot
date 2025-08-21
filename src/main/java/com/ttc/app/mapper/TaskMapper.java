package com.ttc.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ttc.app.dto.task.AddTaskRequest;
import com.ttc.app.dto.task.TaskDto;
import com.ttc.app.entity.TaskDefinitionEntity;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "definitionId", source = "taskDefinition.id")
    TaskDto toDto(com.ttc.app.entity.TaskEntity entity);

    @Mapping(target = "taskDefinition", expression = "java(toTaskDefinition(dto.definitionId()))")
    @Mapping(target = "id", ignore = true)
    com.ttc.app.entity.TaskEntity toEntity(AddTaskRequest dto);

    // Metodo helper per mappare solo l'ID in un oggetto TaskDefinition "stub"
    default TaskDefinitionEntity toTaskDefinition(Long id) {
        if (id == null) return null;
        TaskDefinitionEntity td = new TaskDefinitionEntity();
        td.setId(id);
        return td;
    }
}
