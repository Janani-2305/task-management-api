package com.tms.taskmanagementapi.mapper;

import com.tms.taskmanagementapi.dto.RegistrationDto;
import com.tms.taskmanagementapi.entity.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Registration mapToRegistration(RegistrationDto registrationDto){
        Registration registration = new Registration();
        registration.setName(registrationDto.getName());
        registration.setEmail(registrationDto.getEmail());
        registration.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        registration.setConfirmPassword(passwordEncoder.encode(registrationDto.getConfirmPassword()));
        return registration;
    }

    public RegistrationDto mapToRegistrationDto(Registration registration){
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setEmail(registration.getEmail());
        registrationDto.setPassword(registration.getPassword());
        registrationDto.setConfirmPassword(registration.getConfirmPassword());
        registrationDto.setName(registration.getName());
        return registrationDto;
    }

    public Registration mapToRegistration(RegistrationDto registrationDto, Registration registration){

        if (registrationDto.getName() != null && !registrationDto.getName().isEmpty()) {
            registration.setName(registrationDto.getName());
        }

        if (registrationDto.getEmail() != null && !registrationDto.getEmail().isEmpty()) {
            registration.setEmail(registrationDto.getEmail());
        }

        if (registrationDto.getPassword() != null && !registrationDto.getPassword().isEmpty()) {
            registration.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        }

        if (registrationDto.getConfirmPassword() != null && !registrationDto.getConfirmPassword().isEmpty()) {
            registration.setConfirmPassword(passwordEncoder.encode(registrationDto.getConfirmPassword()));
        }

        return registration;
    }

}
