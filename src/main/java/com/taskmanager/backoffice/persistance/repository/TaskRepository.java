package com.taskmanager.backoffice.persistance.repository;

import com.taskmanager.backoffice.persistance.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}

