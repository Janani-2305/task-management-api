package com.tms.taskmanagementapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(name = "Registration",description = "User Registration details")
public class RegistrationDto {

    @Schema(description = "User's name", example = "Janani K")
    private String name;

    @Schema(description = "User's email", example = "example@gmail.com")
    private String email;

    @Schema(description = "User's password", example = "Abcd@123")
    private String password;

    @Schema(description = "User's confirm Password", example = "Abcd@123")
    private String confirmPassword;

}
