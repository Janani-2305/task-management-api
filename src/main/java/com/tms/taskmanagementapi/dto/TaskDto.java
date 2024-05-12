package com.tms.taskmanagementapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Schema(name = "Task",description = "Task details")
public class TaskDto {

    @Schema(description = "Task Id", example = "1")
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    @NotNull(message = "Name cannot be null")
    @Size(min = 4, message = "The minimum length of the Name should be 4")
    @Schema(description = "Task Name", example = "Home Work 1")
    private String name;

    @NotEmpty(message = "Description cannot be empty")
    @NotNull(message = "Description cannot be null")
    @Schema(description = "Task Description", example = "Home Work to be completed")
    @Size(min = 4, message = "The minimum length of the Description should be 4")
    private String description;

    @NotEmpty(message = "Priority cannot be empty")
    @NotNull(message = "Priority cannot be null")
    @Schema(description = "Task Priority", example = "1")
    private Integer priority;

    @Schema(description = "Task Completion flag", example = "false")
    private boolean completed;

    @NotEmpty(message = "Status cannot be empty")
    @NotNull(message = "Status cannot be null")
    @Schema(description = "Task Completion status", example = "Pending")
    private String status;

    @NotEmpty(message = "TargetDate cannot be empty")
    @NotNull(message = "TargetDate cannot be null")
    @Schema(description = "Task Completion target date", example = "01/12/2024")
    private String targetDate;

    @Schema(description = "Task Completed on date", example = "2024-05-08 09:35:05.540854")
    private LocalDateTime completedOn;

    @Schema(description = "owner of the task", example = "5")
    private Long userId;

    @Schema(description = "Created Date Time", example = "5")
    private LocalDateTime createdAt;

    @Schema(description = "Created By User", example = "5")
    private String createdBy;

    @Schema(description = "Updated Date Time", example = "5")
    private LocalDateTime updatedAt;

    @Schema(description = "Updated By User", example = "5")
    private String updatedBy;
}
