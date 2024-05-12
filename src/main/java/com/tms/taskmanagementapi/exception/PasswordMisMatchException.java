package com.tms.taskmanagementapi.exception;

public class PasswordMisMatchException extends  RuntimeException{
    public PasswordMisMatchException(String message) {
        super(message);
    }
}
