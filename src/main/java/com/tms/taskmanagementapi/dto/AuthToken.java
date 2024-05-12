package com.tms.taskmanagementapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "AuthToken",description = "Auth token holder for token check")
public class AuthToken {

    @Schema(description = "Token value for token check", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYW5hbmlrYWxpeWFuMjNAZ21haWwuY29tIiwiaWF0IjoxNzE1NTA1NzI1LCJleHAiOjE3MTU1MDc1MjV9.4X4cyw3vK6MtOMZ3shuhibuPr9sfYqLx4FkJqQsCJvI")
    private String token;

}
