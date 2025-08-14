package com.ttc.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ttc.app.dto.task.Task;
import com.ttc.app.entity.TaskDefinition;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "definitionId", source = "taskDefinition.id")
    Task toDto(com.ttc.app.entity.Task model);

    @Mapping(target = "taskDefinition", expression = "java(toTaskDefinition(dto.definitionId()))")
    com.ttc.app.entity.Task toEntity(Task dto);

    // Metodo helper per mappare solo l'ID in un oggetto TaskDefinition "stub"
    default TaskDefinition toTaskDefinition(Long id) {
        if (id == null) return null;
        TaskDefinition td = new TaskDefinition();
        td.setId(id);
        return td;
    }
}
