package com.resimanager.backoffice.service;

import com.resimanager.backoffice.dto.TaskRequestDto;
import com.resimanager.backoffice.dto.TaskResponseDto;
import com.resimanager.backoffice.persistance.entity.Task;
import com.resimanager.backoffice.persistance.repository.TaskRepository;
import com.resimanager.backoffice.service.mapper.TaskMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Page<TaskResponseDto> getAllTask(Pageable pageable) {
        var list = taskRepository.findAll(pageable).map(TaskMapper.MAPPER::mapToDto);
        var countConstruction = taskRepository.count();
        return new PageImpl<>(list.getContent(), pageable, countConstruction);
    }

    public TaskResponseDto getTaskById(Long ownerId) {
        return taskRepository.findById(ownerId)
                .map(TaskMapper.MAPPER::mapToDto)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
    }

    public TaskResponseDto createTask(TaskRequestDto owner) {
        Task savedOwner = taskRepository.save(TaskMapper.MAPPER.mapToEntity(owner));
        return TaskMapper.MAPPER.mapToDto(savedOwner);
    }

    public Optional<TaskResponseDto> updateTask(Long taskId, Task ownerDetails) {
        return taskRepository.findById(taskId).map(task -> {
            task.setTitle(ownerDetails.getTitle());
            task.setDescription(ownerDetails.getDescription());
            task.setDueDate(ownerDetails.getDueDate());
            task.setState(ownerDetails.getState());
            Task updatedOwner = taskRepository.save(task);
            return TaskMapper.MAPPER.mapToDto(updatedOwner);
        });
    }

    public void deleteTask(Long ownerId) {
        taskRepository.deleteById(ownerId);
    }
}
