package com.tms.taskmanagementapi.util;

import com.tms.taskmanagementapi.dto.RegistrationDto;
import com.tms.taskmanagementapi.dto.User;
import com.tms.taskmanagementapi.entity.Registration;
import com.tms.taskmanagementapi.exception.PasswordMisMatchException;
import com.tms.taskmanagementapi.service.TmsUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class TmsUtil {

    public void setUserId(){
        User.userId = getUserDetails().getUserId();
        User.userName = getUserDetails().getName();
    }

    private TmsUserDetails getUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (TmsUserDetails) authentication.getPrincipal();
    }

    public void passwordMatcher(RegistrationDto registrationDto){
        if(! registrationDto.getPassword().equals(registrationDto.getConfirmPassword())){
            throw new PasswordMisMatchException("Password and Confirm Password are not matching");
        }
    }

}
