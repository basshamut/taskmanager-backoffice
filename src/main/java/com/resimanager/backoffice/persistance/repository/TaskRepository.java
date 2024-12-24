package com.resimanager.backoffice.persistance.repository;

import com.resimanager.backoffice.persistance.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}

