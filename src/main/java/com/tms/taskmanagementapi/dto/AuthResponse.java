package com.tms.taskmanagementapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Login Response", description = "Response after successful login")
public class AuthResponse {

    @Schema(description = "Token for subsequent authentication", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0YW1pemhAZ21haWwuY29tIiwiaWF0IjoxNzE1NDMxNjIxLCJleHAiOjE3MTU0MzM0MjF9.DF6OY-ZWVOoOVI9M-uBNb4Afg5nOPGmFaE1mjypSxXg")
    private String token;

    @Schema(description = "User's name", example = "Janani K")
    private String name;

    @Schema(description = "User name for login", example = "example@gmail.com")
    private String userName;

}
