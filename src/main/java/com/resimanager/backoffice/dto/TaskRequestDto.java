package com.resimanager.backoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDto {
    private String title;
    private String description;
    private String dueDate;
    private String state;
}

