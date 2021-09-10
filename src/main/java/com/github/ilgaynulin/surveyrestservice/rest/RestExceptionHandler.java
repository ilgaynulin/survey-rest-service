package com.github.ilgaynulin.surveyrestservice.rest;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.github.ilgaynulin.surveyrestservice.dto.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Class specifies how to handle specific exceptions in application during work
 * @author ilgaynulin
 */

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponseDto> illegalArgumentException(IllegalArgumentException ex) {
        ExceptionResponseDto response = new ExceptionResponseDto();
        response.setStatusCode(HttpStatus.BAD_REQUEST.toString().substring(0,3));
        response.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        response.setMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<ExceptionResponseDto>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles JSON parsing exceptions. Method hides code structure information from message.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponseDto> parseException(HttpMessageNotReadableException ex) {
        ExceptionResponseDto response = new ExceptionResponseDto();
        response.setStatusCode(HttpStatus.BAD_REQUEST.toString().substring(0,3));
        response.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        if(ex.getCause() instanceof JsonMappingException) {
            JsonMappingException jsonMappingException = (JsonMappingException)ex.getCause();
            response.setMessage(jsonMappingException.getOriginalMessage());
        } else {
            response.setMessage(ex.getMessage());
        }
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<ExceptionResponseDto>(response, HttpStatus.BAD_REQUEST);
    }
}
