package com.scaleorange.LaptopRentals.exceptionHandlers;

import com.scaleorange.LaptopRentals.dto.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyAppExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse exceptionHandler(RuntimeException exception){
        return new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
