package com.resimanager.backoffice.service.mapper;

import com.resimanager.backoffice.dto.TaskRequestDto;
import com.resimanager.backoffice.dto.TaskResponseDto;
import com.resimanager.backoffice.persistance.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", imports = {Task.class, TaskResponseDto.class, TaskRequestDto.class})
public interface TaskMapper {
    TaskMapper MAPPER = Mappers.getMapper(TaskMapper.class);

    TaskResponseDto mapToDto(Task entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "state", source = "state")
    @Mapping(source = "dueDate", target = "dueDate", dateFormat = "yyyy-MM-dd")
    Task mapToEntity(TaskRequestDto dto);

}
