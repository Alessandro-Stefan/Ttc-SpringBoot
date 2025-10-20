package com.ttc.app.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ttc.app.dto.taskDefinition.AddTaskDefinitionRequest;
import com.ttc.app.dto.taskDefinition.TaskDefinitionDto;
import com.ttc.app.entity.TaskDefinitionEntity;

@Mapper(componentModel = "spring")
public interface TaskDefinitionMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "createdAt", expression = "java(dateToString(model.getCreatedAt()))")
    @Mapping(target = "updatedAt", expression = "java(dateToString(model.getUpdatedAt()))")
    TaskDefinitionDto toDto (com.ttc.app.entity.TaskDefinitionEntity model);

    List<TaskDefinitionDto> toDtos (List<TaskDefinitionEntity> models);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", expression = "java(toDateNow())")
    @Mapping(target = "updatedAt", ignore = true)
    com.ttc.app.entity.TaskDefinitionEntity toEntity (AddTaskDefinitionRequest dto);

    default LocalDateTime toDateNow() {
        return LocalDateTime.now();
    }

    default String dateToString(LocalDateTime date) {
        if (date != null)
            return date.toString();

        return null;
    }
}
