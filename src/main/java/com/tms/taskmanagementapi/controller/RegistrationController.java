package com.tms.taskmanagementapi.controller;

import com.tms.taskmanagementapi.dto.RegistrationDto;
import com.tms.taskmanagementapi.dto.ResponseDto;
import com.tms.taskmanagementapi.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/user")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerUser(@RequestBody RegistrationDto registrationDto){

        ResponseDto responseDto = registrationService.saveUser(registrationDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<RegistrationDto> getUser(@PathVariable String email){
        RegistrationDto registrationDto = registrationService.getUserByEmail(email);
        return new ResponseEntity<>(registrationDto, HttpStatus.OK);
    }

    @PutMapping("/{email}")
    public ResponseEntity<ResponseDto> updaterUser(@RequestBody RegistrationDto registrationDto, @PathVariable String email){

        ResponseDto responseDto = registrationService.updateUser(registrationDto, email);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/validate-username")
    public ResponseEntity<ResponseDto> validateUserName(@RequestBody String email){
        ResponseDto responseDto = registrationService.validateUserName(email);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/update-password")
    public ResponseEntity<ResponseDto> updatePassword(@RequestBody RegistrationDto registrationDto){
        ResponseDto responseDto = registrationService.updatePassword(registrationDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
