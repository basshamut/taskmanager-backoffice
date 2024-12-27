package com.taskmanager.backoffice.controller;

import com.taskmanager.backoffice.dto.TaskRequestDto;
import com.taskmanager.backoffice.dto.TaskResponseDto;
import com.taskmanager.backoffice.exception.ServiceException;
import com.taskmanager.backoffice.persistance.entity.Task;
import com.taskmanager.backoffice.service.TaskService;
import com.taskmanager.backoffice.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponseDto>> getAllOwners(@RequestParam int page, @RequestParam int size) {
        if (!Tools.isValidPagination(page, size)) {
            throw new ServiceException("Invalid pagination parameters", HttpStatus.BAD_REQUEST.value());
        }

        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<TaskResponseDto> owners = taskService.getAllTask(pageable);
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> findById(@PathVariable Long id) {
        try {
            TaskResponseDto owner = taskService.getTaskById(id);
            return new ResponseEntity<>(owner, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> createOwner(@RequestBody TaskRequestDto owner) {
        TaskResponseDto createdOwner = taskService.createTask(owner);
        return new ResponseEntity<>(createdOwner, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateOwner(@PathVariable Long id, @RequestBody Task ownerDetails) {
        Optional<TaskResponseDto> updatedOwner = taskService.updateTask(id, ownerDetails);
        return updatedOwner.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
