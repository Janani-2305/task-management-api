package com.tms.taskmanagementapi.service;

import com.tms.taskmanagementapi.constants.GlobalConstants;
import com.tms.taskmanagementapi.dto.ResponseDto;
import com.tms.taskmanagementapi.dto.TaskDto;
import com.tms.taskmanagementapi.dto.UserId;
import com.tms.taskmanagementapi.entity.Task;
import com.tms.taskmanagementapi.exception.ResourceNotFoundException;
import com.tms.taskmanagementapi.mapper.ResponseMapper;
import com.tms.taskmanagementapi.mapper.TaskMapper;
import com.tms.taskmanagementapi.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    public ResponseDto addTask(TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        taskRepository.save(task);
        return ResponseMapper
                .mapToResponseDto(HttpStatus.CREATED, GlobalConstants.MESSAGE_201);
    }

    public ResponseDto updateTask(TaskDto taskDto, Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(GlobalConstants.EXCEPTION_MESSAGE_NOT_FOUND, id)));

        Task taskUpdated = taskMapper.mapToTask(task, taskDto);
        taskRepository.save(taskUpdated);
        return ResponseMapper
                .mapToResponseDto(HttpStatus.OK, GlobalConstants.MESSAGE_200);
    }

    public List<TaskDto> getAllTasks(String status) {
        log.info(UserId.userId.toString());
        List<TaskDto> tasks = taskRepository.findAllByUserId(UserId.userId)
                .stream()
                .map(task -> taskMapper.mapToTaskDto(task))
                .collect(Collectors.toList());

        return tasks;
    }

    private List<Task> getTasks(List<Task> tasks, String status) {

        if (!status.isBlank() || !status.isEmpty() || !status.equalsIgnoreCase("All")) {

            return tasks
                    .stream()
                    .filter(task -> task.getStatus().equalsIgnoreCase(status))
                    .sorted()
                    .collect(Collectors.toList());
        }
        return tasks;
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(GlobalConstants.EXCEPTION_MESSAGE_NOT_FOUND, id)));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<TaskDto> searchByName(String searchString) {
        List<Task> tasks = taskRepository.findAllByUserIdAndNameContaining(UserId.userId, searchString);

        if(tasks.isEmpty()){
            throw new ResourceNotFoundException("There is no Resource found for the give search key "+ searchString);
        }

        return taskRepository.findAllByUserIdAndNameContaining(UserId.userId, searchString)
                .stream()
                .map(task -> taskMapper.mapToTaskDto(task))
                .collect(Collectors.toList());
    }


}
