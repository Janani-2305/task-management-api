package com.tms.taskmanagementapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@Schema(name = "Response", description = "Response details")
public class ResponseDto {

    @Schema(description = "Status code of the response", example = "200")
    private HttpStatus statusCode;

    @Schema(description = "Response message", example = "Your request has been processed successfully")
    private String message;
}
