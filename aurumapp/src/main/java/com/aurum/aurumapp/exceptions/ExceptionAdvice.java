package com.aurum.aurumapp.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    @ExceptionHandler(value = {Exception.class})
    ResponseEntity<Object> handleGeneralException(Exception e) {
        log.error("Uncaught exception, message={}", e.getMessage(), e);
        return status(INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException badCredentialsException){
        return status(NOT_FOUND).body(badCredentialsException.getMessage());
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    ResponseEntity<Object> handleNotFoundException(ResourceNotFoundException resourceNotFoundException){
        return status(NOT_FOUND).body(resourceNotFoundException.getMessage());
    }
}
