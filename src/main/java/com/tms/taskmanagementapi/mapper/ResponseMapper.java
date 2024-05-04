package com.tms.taskmanagementapi.mapper;

import com.tms.taskmanagementapi.dto.ResponseDto;
import org.springframework.http.HttpStatus;

public class ResponseMapper {

    public static ResponseDto mapToResponseDto(HttpStatus statusCode, String message){

        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatusCode(statusCode);
        responseDto.setMessage(message);
        return responseDto;

    }
}
