package com.rsvpagentprocessing.rsvpagentprocessing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.NoSuchFileException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NoSuchFileException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataNotFound(NoSuchFileException exception) {
        return new ResponseEntity<ErrorResponseDTO>(ErrorResponseDTO.builder()
                .errorDetails("File not found !!!")
                .errorStatusCode("404").build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataNotFound(RuntimeException exception) {
        return new ResponseEntity<ErrorResponseDTO>(ErrorResponseDTO.builder()
                .errorDetails("No matching data found !!!")
                .errorStatusCode("404").build(), HttpStatus.NOT_FOUND);
    }
}
