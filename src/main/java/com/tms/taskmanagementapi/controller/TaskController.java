package com.tms.taskmanagementapi.controller;

import com.tms.taskmanagementapi.dto.ErrorResponseDto;
import com.tms.taskmanagementapi.dto.ResponseDto;
import com.tms.taskmanagementapi.dto.TaskDto;
import com.tms.taskmanagementapi.entity.Task;
import com.tms.taskmanagementapi.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Task - Rest APIs",
        description = "This helps to make CRUD Operations on Tasks"
)
@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam String status){

        return new ResponseEntity<>(taskService.getAllTasks(status), HttpStatus.OK);
    }

    @Operation(
            summary = "Create Task API",
            description = "This API will create the Task"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS : CREATED"
    )
    @PostMapping("/task")
    public ResponseEntity<ResponseDto> addTask(@RequestBody TaskDto taskDto){
        return new ResponseEntity<>(taskService.addTask(taskDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Task API",
            description = "This API will get the Task"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP STATUS : NOT FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    )
    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id){
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/task")
    public ResponseEntity<ResponseDto> updateTask(@RequestBody TaskDto taskDto){
        return new ResponseEntity<>(taskService.addTask(taskDto), HttpStatus.OK);
    }

    @GetMapping("/task/search")
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam String searchString,@RequestParam String status){
        return new ResponseEntity<>(taskService.searchByName(searchString, status), HttpStatus.OK);
    }

    @PatchMapping("/task/{id}")
    public ResponseEntity<ResponseDto> updateStatus(@PathVariable Long id, @RequestBody TaskDto taskDto){
        return new ResponseEntity<>(taskService.updateTask(taskDto, id), HttpStatus.OK);
    }

}
