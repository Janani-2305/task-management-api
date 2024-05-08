package com.tms.taskmanagementapi.service;

import com.tms.taskmanagementapi.constants.GlobalConstants;
import com.tms.taskmanagementapi.dto.RegistrationDto;
import com.tms.taskmanagementapi.dto.ResponseDto;
import com.tms.taskmanagementapi.entity.Registration;
import com.tms.taskmanagementapi.exception.ResourceNotFoundException;
import com.tms.taskmanagementapi.mapper.RegistrationMapper;
import com.tms.taskmanagementapi.mapper.ResponseMapper;
import com.tms.taskmanagementapi.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    public ResponseDto saveUser(RegistrationDto registrationDto){

        Registration registration = RegistrationMapper.mapToRegistration(registrationDto);
        registrationRepository.save(registration);
        return ResponseMapper
                .mapToResponseDto(HttpStatus.CREATED, GlobalConstants.MESSAGE_201);
    }

    public RegistrationDto getUserByEmail(String email){

        Registration registration = registrationRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(GlobalConstants.EXCEPTION_MESSAGE_NOT_FOUND, email)));
        return RegistrationMapper.mapToRegistrationDto(registration);
    }

    public ResponseDto updateUser(RegistrationDto registrationDto, String email){
        Registration registration = registrationRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(GlobalConstants.EXCEPTION_MESSAGE_NOT_FOUND, email)));
        Registration registrationUpdated = RegistrationMapper.mapToRegistration(registrationDto, registration);
        registrationRepository.save(registrationUpdated);
        return ResponseMapper
                .mapToResponseDto(HttpStatus.OK, GlobalConstants.MESSAGE_200);
    }

    public ResponseDto validateUserName(String email){
        Registration registration = registrationRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(GlobalConstants.EXCEPTION_MESSAGE_NOT_FOUND, email)));
        return ResponseMapper
                .mapToResponseDto(HttpStatus.OK, GlobalConstants.MESSAGE_200);
    }

    public ResponseDto updatePassword(RegistrationDto registrationDto){
        Registration registration = registrationRepository.findByEmail(registrationDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(String.format(GlobalConstants.EXCEPTION_MESSAGE_NOT_FOUND, registrationDto.getEmail())));
        Registration registrationUpdated = RegistrationMapper.mapToRegistration(registrationDto, registration);
        registrationRepository.save(registrationUpdated);
        return ResponseMapper
                .mapToResponseDto(HttpStatus.OK, GlobalConstants.MESSAGE_200);
    }

}
