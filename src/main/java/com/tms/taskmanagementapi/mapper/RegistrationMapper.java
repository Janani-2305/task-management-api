package com.tms.taskmanagementapi.mapper;

import com.tms.taskmanagementapi.dto.RegistrationDto;
import com.tms.taskmanagementapi.entity.Registration;

public class RegistrationMapper {

    public static Registration mapToRegistration(RegistrationDto registrationDto){
        Registration registration = new Registration();
        registration.setName(registrationDto.getName());
        registration.setEmail(registrationDto.getEmail());
        registration.setPassword(registrationDto.getPassword());
        registration.setConfirmPassword(registrationDto.getConfirmPassword());
        return registration;
    }

    public static RegistrationDto mapToRegistrationDto(Registration registration){
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setEmail(registration.getEmail());
        registrationDto.setPassword(registration.getPassword());
        registrationDto.setConfirmPassword(registration.getConfirmPassword());
        registrationDto.setName(registration.getName());
        return registrationDto;
    }

    public static Registration mapToRegistration(RegistrationDto registrationDto, Registration registration){
        registration.setName(registrationDto.getName());
        registration.setEmail(registrationDto.getEmail());
        registration.setPassword(registrationDto.getPassword());
        registration.setConfirmPassword(registrationDto.getConfirmPassword());
        return registration;
    }

}
