package com.tms.taskmanagementapi.mapper;

import com.tms.taskmanagementapi.dto.TaskDto;
import com.tms.taskmanagementapi.entity.Task;
import com.tms.taskmanagementapi.service.TmsUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        task.setUserId(getUserDetails().getUserId());
        if(taskDto.isCompleted()){
            task.setCompletedOn(LocalDateTime.now());
        }else{
            task.setCompletedOn(null);
        }
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
        task.setUserId(getUserDetails().getUserId());

        return task;
    }

    private TmsUserDetails getUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (TmsUserDetails) authentication.getPrincipal();
    }

}
