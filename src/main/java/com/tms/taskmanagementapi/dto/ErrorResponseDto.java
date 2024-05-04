package com.tms.taskmanagementapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ErrorResponseDto {

    @Schema(
            description = "This field provides api Path",
            example = "/api/v1/task"
    )
    private String apiPath;

    @Schema(
            description = "This field provides error code",
            example = "404"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "This field provides error message",
            example = "Task with id 5 is not present"
    )
    private String errorMessage;

    @Schema(
            description = "This field provides error time"
    )
    private LocalDateTime errorTime;

}
