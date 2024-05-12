package com.tms.taskmanagementapi.dto;

import com.tms.taskmanagementapi.constants.GlobalConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Login", description = "Login details")
public class AuthRequest {

    @Schema(description = "User name for login", example = "example@gmail.com")
    @NotEmpty(message = "UserName cannot be empty")
    @Email(message = "UserName should be a valid email id")
    @NotNull(message = "UserName cannot be null")
    private String userName;

    @Schema(description = "User's password", example = "Abcd@123")
    @NotEmpty(message = "Password cannot be empty")
    @NotNull(message = "Password cannot be null")
    private String password;

}
