package com.github.ilgaynulin.surveyrestservice.rest;

import com.github.ilgaynulin.surveyrestservice.dto.ExceptionResponseDto;
import com.github.ilgaynulin.surveyrestservice.exceptions.InvalidDateFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(InvalidDateFormatException.class)
    public ResponseEntity<ExceptionResponseDto> invalidDateFormat(InvalidDateFormatException ex) {
        ExceptionResponseDto response = new ExceptionResponseDto();
        response.setStatusCode(HttpStatus.BAD_REQUEST.toString().substring(0,3));
        response.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        response.setMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<ExceptionResponseDto>(response, HttpStatus.BAD_REQUEST);
    }
}
