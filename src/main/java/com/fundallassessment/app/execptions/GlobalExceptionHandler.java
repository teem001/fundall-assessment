package com.fundallassessment.app.execptions;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


//    @ExceptionHandler(ResourceNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ResponseBody
//    public ApiResponse<String> handleNotFoundException(ResourceNotFoundException ex){
//        log.error(ex.getMessage());
//        return  new ApiResponse<>("Failed","Error: " + ex.getMessage(), null);
//    }
//
//    @ExceptionHandler(ValidationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public ApiResponse<String> handleValidationException(ValidationException ex){
//        log.error(ex.getMessage());
//        return  new ApiResponse<>("Failed","Error: "+ex.getMessage(), null);
//    }
//
//
//
//
//    @ExceptionHandler(InvalidTransactionException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public ApiResponse<String> handleInvalidTransactionException(InvalidTransactionException ex){
//        log.error(ex.getMessage());
//        return  new ApiResponse<>("Failed","Error: "+ex.getMessage(), null);
//    }
//
//    @ExceptionHandler(UserNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ResponseBody
//    public ApiResponse<String> handleUserNotFoundException(UserNotFoundException ex){
//        log.error(ex.getMessage());
//        return  new ApiResponse<>("Failed","Error: "+ex.getMessage(), null);
//    }
//
//
//    //Catches unauthorized access exceptions thrown by Spring Security even before the controller is executed
//    @ExceptionHandler(org.springframework.security.core.AuthenticationException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    @ResponseBody
//    public ApiResponse<String> handleUnAuthorizedException(org.springframework.security.core.AuthenticationException ex){
//        log.error(ex.getMessage());
//        return  new ApiResponse<>("Failed","Error: " + ex.getMessage(),null);
//
//    }
//    @ExceptionHandler(InvalidCredentialsException.class)
//    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
//    @ResponseBody
//    public ApiResponse<String> handleInvalidCredentialsException(InvalidCredentialsException ex){
//        log.error(ex.getMessage());
//        return  new ApiResponse<>("Failed", "Error: " +ex.getMessage(),null);
//
//    }
//
//    @ExceptionHandler(java.lang.UnsupportedOperationException.class)
//    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
//    @ResponseBody
//    public ApiResponse<String> handleUnsupportedOperationException(java.lang.UnsupportedOperationException ex){
//        log.error(ex.getMessage());
//        return  new ApiResponse<>("Failed", "Error: " +ex.getMessage(),null);
//
//    }
//    @ExceptionHandler(InsufficientFundsException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public ApiResponse<String> handleInsufficientFundsException(InsufficientFundsException ex){
//        log.error(ex.getMessage());
//        return  new ApiResponse<>("Failed", ex.getMessage(),null);
//
//    }
}