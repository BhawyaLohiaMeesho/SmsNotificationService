package com.notification.sms.exceptionhandler;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
@Component
public class RestExceptionHandler {
    @ExceptionHandler
    public String handleException(Exception e){
        return e.getMessage();
    }
}
