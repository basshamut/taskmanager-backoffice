package com.taskmanager.backoffice.service.mapper;

import com.taskmanager.backoffice.dto.TaskRequestDto;
import com.taskmanager.backoffice.dto.TaskResponseDto;
import com.taskmanager.backoffice.persistance.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", imports = {Task.class, TaskResponseDto.class, TaskRequestDto.class})
public interface TaskMapper {
    TaskMapper MAPPER = Mappers.getMapper(TaskMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "state", source = "state")
    @Mapping(source = "dueDate", target = "dueDate", dateFormat = "dd/MM/yyyy")
    TaskResponseDto mapToDto(Task entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "state", source = "state")
    @Mapping(source = "dueDate", target = "dueDate", dateFormat = "dd/MM/yyyy")
    Task mapToEntity(TaskRequestDto dto);

}
