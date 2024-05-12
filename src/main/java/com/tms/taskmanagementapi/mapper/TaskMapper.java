package com.tms.taskmanagementapi.mapper;

import com.tms.taskmanagementapi.dto.TaskDto;
import com.tms.taskmanagementapi.dto.User;
import com.tms.taskmanagementapi.entity.Task;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskMapper {

    public TaskDto mapToTaskDto(Task task){
        TaskDto taskDto = new TaskDto();
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setPriority(task.getPriority());
        taskDto.setCompleted(task.isCompleted());
        taskDto.setStatus(task.getStatus());
        taskDto.setTargetDate(task.getTargetDate());
        taskDto.setCompletedOn(task.getCompletedOn());
        taskDto.setUserId(task.getUserId());
        taskDto.setId(task.getId());
        taskDto.setCreatedAt(task.getCreatedAt());
        taskDto.setCreatedBy(task.getCreatedBy());
        taskDto.setUpdatedAt(task.getUpdatedAt());
        taskDto.setUpdatedBy(task.getUpdatedBy());
        return taskDto;
    }

    public Task mapToTask(TaskDto taskDto){
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setPriority(taskDto.getPriority());
        task.setCompleted(taskDto.isCompleted());
        task.setStatus(taskDto.getStatus());
        task.setTargetDate(taskDto.getTargetDate());
        task.setUserId(User.userId);
        if(taskDto.isCompleted()){
            task.setCompletedOn(LocalDateTime.now());
        }else{
            task.setCompletedOn(null);
        }
        //task.setCompletedOn(taskDto.getCompletedOn());
        return task;
    }

    public Task mapToTask(Task task, TaskDto taskDto){

        if(taskDto.isCompleted()){
            task.setCompletedOn(LocalDateTime.now());
        }else{
            task.setCompletedOn(null);
        }
        task.setCompleted(taskDto.isCompleted());
        task.setStatus(taskDto.getStatus());
        task.setUserId(User.userId);

        return task;
    }

}
