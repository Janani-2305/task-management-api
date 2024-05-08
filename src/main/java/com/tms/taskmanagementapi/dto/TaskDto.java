package com.tms.taskmanagementapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@Schema(name = "Task",description = "Task details")
public class TaskDto {

    @Schema(description = "Task Name", example = "Home Work 1")
    private String name;

    @Schema(description = "Task Description", example = "Home Work to be completed")
    private String description;

    @Schema(description = "Task Priority", example = "1")
    private Integer priority;

    @Schema(description = "Task Completion flag", example = "false")
    private boolean completed;

    @Schema(description = "Task Completion status", example = "Pending")
    private String status;

    @Schema(description = "Task Completion target date", example = "01/12/2024")
    private Date targetDate;

    @Schema(description = "Task Completed on date", example = "2024-05-08 09:35:05.540854")
    private LocalDateTime completedOn;

}
