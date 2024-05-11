package com.tms.taskmanagementapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Login", description = "Login details")
public class AuthRequest {

    @Schema(description = "User name for login", example = "example@gmail.com")
    private String userName;

    @Schema(description = "User's password", example = "Abcd@123")
    private String password;

}
