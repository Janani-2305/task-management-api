package com.tms.taskmanagementapi.util;

import com.tms.taskmanagementapi.dto.UserId;
import com.tms.taskmanagementapi.service.TmsUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class TmsUtil {

    public void setUserId(){
        UserId.userId = getUserDetails().getUserId();
    }

    private TmsUserDetails getUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (TmsUserDetails) authentication.getPrincipal();
    }

}
