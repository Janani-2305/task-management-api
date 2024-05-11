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
        name = "Task APIs",
        description = "This helps to make CRUD Operations on Tasks"
)
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(
            summary = "Get All Tasks",
            description = "This API will return the list of tasks for a user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP STATUS : OK"),
            @ApiResponse(responseCode = "401", description = "HTTP STATUS : UNAUTHORIZED",content = @Content),
            @ApiResponse(responseCode = "500", description = "HTTP STATUS : INTERNAL_SERVER_ERROR",content = @Content)
    })
    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks(@RequestParam String status) {

        return new ResponseEntity<>(taskService.getAllTasks(status), HttpStatus.OK);
    }

    @Operation(
            summary = "Create Task API",
            description = "This API will create the Task"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "HTTP STATUS : CREATED"),
            @ApiResponse(responseCode = "401", description = "HTTP STATUS : UNAUTHORIZED",content = @Content),
            @ApiResponse(responseCode = "500", description = "HTTP STATUS : INTERNAL_SERVER_ERROR",content = @Content)
    })
    @PostMapping("/task")
    public ResponseEntity<ResponseDto> addTask(@RequestBody TaskDto taskDto) {
        return new ResponseEntity<>(taskService.addTask(taskDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Task API using task id",
            description = "This API will return the Task for the given task id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP STATUS : OK"),
            @ApiResponse(responseCode = "401", description = "HTTP STATUS : UNAUTHORIZED",content = @Content),
            @ApiResponse(responseCode = "404",description = "HTTP STATUS : NOT FOUND",content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "HTTP STATUS : INTERNAL_SERVER_ERROR",content = @Content)
    })
    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Task API using task id",
            description = "This API will delete the Task for the given task id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP STATUS : OK"),
            @ApiResponse(responseCode = "401", description = "HTTP STATUS : UNAUTHORIZED",content = @Content),
            @ApiResponse(responseCode = "404",description = "HTTP STATUS : NOT FOUND",content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "HTTP STATUS : INTERNAL_SERVER_ERROR",content = @Content)
    })
    @DeleteMapping("/task/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Update Task API",
            description = "This API will update the given Task with existing Task"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP STATUS : OK"),
            @ApiResponse(responseCode = "401", description = "HTTP STATUS : UNAUTHORIZED",content = @Content),
            @ApiResponse(responseCode = "404",description = "HTTP STATUS : NOT FOUND",content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "HTTP STATUS : INTERNAL_SERVER_ERROR",content = @Content)
    })
    @PutMapping("/task")
    public ResponseEntity<ResponseDto> updateTask(@RequestBody TaskDto taskDto) {
        return new ResponseEntity<>(taskService.addTask(taskDto), HttpStatus.OK);
    }

    @Operation(
            summary = "Search Task by giving Task Name as Search key",
            description = "This API will update the given Task with existing Task"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP STATUS : OK"),
            @ApiResponse(responseCode = "401", description = "HTTP STATUS : UNAUTHORIZED",content = @Content),
            @ApiResponse(responseCode = "404",description = "HTTP STATUS : NOT FOUND",content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "HTTP STATUS : INTERNAL_SERVER_ERROR",content = @Content)
    })
    @GetMapping("/task/search")
    public ResponseEntity<List<TaskDto>> searchTask(@RequestParam String searchString) {
        return new ResponseEntity<>(taskService.searchByName(searchString), HttpStatus.OK);
    }

    @Operation(
            summary = "Update Task Status",
            description = "This API will update the status of the Task"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "HTTP STATUS : OK"),
            @ApiResponse(responseCode = "401", description = "HTTP STATUS : UNAUTHORIZED",content = @Content),
            @ApiResponse(responseCode = "404",description = "HTTP STATUS : NOT FOUND",content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "HTTP STATUS : INTERNAL_SERVER_ERROR",content = @Content)
    })
    @PatchMapping("/task/{id}")
    public ResponseEntity<ResponseDto> updateStatus(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        return new ResponseEntity<>(taskService.updateTask(taskDto, id), HttpStatus.OK);
    }

}
