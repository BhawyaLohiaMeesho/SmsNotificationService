package com.notification.sms.exceptionhandler;

import com.notification.sms.constant.ExceptionCode;
import com.notification.sms.exceptions.*;
import com.notification.sms.response.ErrorResponse;
import com.notification.sms.response.ErrorResponseDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
@Component
public class RestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(InvalidPhoneNumberException e){
        String code= ExceptionCode.INVALID_REQUEST;
        String message="Phone number is not valid";
        ErrorResponse errorResponse=new ErrorResponse(new ErrorResponseDetails(code,message));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(NullPhoneNumberException e){
        String code=ExceptionCode.INVALID_REQUEST;
        String message="Phone number is mandatory";
        ErrorResponse errorResponse=new ErrorResponse(new ErrorResponseDetails(code,message));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(NullPhoneNumberListException e){
        String code=ExceptionCode.INVALID_REQUEST;
        String message="Phone number list is mandatory";
        ErrorResponse errorResponse=new ErrorResponse(new ErrorResponseDetails(code,message));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(NullItemPhoneNumberListException e){
        String code=ExceptionCode.INVALID_REQUEST;
        String message="One or more phone numbers in phone number list are null";
        ErrorResponse errorResponse=new ErrorResponse(new ErrorResponseDetails(code,message));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(NullRequestBodyException e){
        String code=ExceptionCode.INVALID_REQUEST;
        String message="Request body is null";
        ErrorResponse errorResponse=new ErrorResponse(new ErrorResponseDetails(code,message));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(NullStartDateTimeException e){
        String code=ExceptionCode.INVALID_REQUEST;
        String message="Start date time should not be null";
        ErrorResponse errorResponse=new ErrorResponse(new ErrorResponseDetails(code,message));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(NullEndDateTimeException e){
        String code=ExceptionCode.INVALID_REQUEST;
        String message="End date time should not be null";
        ErrorResponse errorResponse=new ErrorResponse(new ErrorResponseDetails(code,message));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(NullTextException e){
        String code=ExceptionCode.INVALID_REQUEST;
        String message="text should not be null";
        ErrorResponse errorResponse=new ErrorResponse(new ErrorResponseDetails(code,message));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(RequestWithGivenIdNotFoundException e){
        String code=ExceptionCode.INVALID_REQUEST;
        String message="No sms request found with given request id";
        ErrorResponse errorResponse=new ErrorResponse(new ErrorResponseDetails(code,message));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(NullMessageException e){
        String code=ExceptionCode.INVALID_REQUEST;
        String message="Message should not be null";
        ErrorResponse errorResponse=new ErrorResponse(new ErrorResponseDetails(code,message));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception e){
        String code=ExceptionCode.INTERNAL_SERVER_ERROR;
        String message="Something went wrong. Please try again later";
        ErrorResponse errorResponse=new ErrorResponse(new ErrorResponseDetails(code,message));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
