package com.tms.taskmanagementapi.mapper;

import com.tms.taskmanagementapi.dto.TaskDto;
import com.tms.taskmanagementapi.entity.Task;

import java.time.LocalDateTime;

public class TaskMapper {

    public static TaskDto mapToTaskDto(Task task){
        TaskDto taskDto = new TaskDto();
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setPriority(task.getPriority());
        taskDto.setCompleted(task.isCompleted());
        taskDto.setStatus(task.getStatus());
        taskDto.setTargetDate(task.getTargetDate());
        taskDto.setCompletedOn(task.getCompletedOn());
        return taskDto;
    }

    public static Task mapToTask(TaskDto taskDto){
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setPriority(taskDto.getPriority());
        task.setCompleted(taskDto.isCompleted());
        task.setStatus(taskDto.getStatus());
        task.setTargetDate(taskDto.getTargetDate());
        if(taskDto.isCompleted()){
            task.setCompletedOn(LocalDateTime.now());
        }else{
            task.setCompletedOn(null);
        }
        return task;
    }

    public static Task mapToTask(Task task, TaskDto taskDto){

        if(taskDto.isCompleted()){
            task.setCompletedOn(LocalDateTime.now());
        }else{
            task.setCompletedOn(null);
        }
        task.setCompleted(taskDto.isCompleted());
        task.setStatus(taskDto.getStatus());

        return task;
    }

}
