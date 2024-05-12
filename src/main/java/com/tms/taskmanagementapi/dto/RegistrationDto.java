package com.tms.taskmanagementapi.dto;

import com.tms.taskmanagementapi.constants.GlobalConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(name = "Registration",description = "User Registration details")
public class RegistrationDto {

    @Schema(description = "User's name", example = "Janani K")
    @NotEmpty(message = "Name cannot be empty")
    @NotNull(message = "Name cannot be null")
    private String name;

    @Schema(description = "User's email", example = "example@gmail.com")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email address should be a valid value")
    @NotNull(message = "Email cannot be null")
    private String email;

    @Schema(description = "User's password", example = "Abcd@123")
    @NotEmpty(message = "Password cannot be empty")
    @Pattern(regexp = GlobalConstants.PASSWORD_PATTERN, message = "Password must contain at least one Upper Case, one Lower case, One number, One Special Character, Total of 8 Characters")
    @NotNull(message = "Password cannot be null")
    private String password;

    @Schema(description = "User's confirm Password", example = "Abcd@123")
    @NotEmpty(message = "Confirm Password cannot be empty")
    @Pattern(regexp = GlobalConstants.PASSWORD_PATTERN, message = "Confirm Password must contain at least one Upper Case, one Lower case, One number, One Special Character, Total of 8 Characters")
    @NotNull(message = "Confirm Password cannot be null")
    private String confirmPassword;

}
