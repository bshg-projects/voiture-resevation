package com.example.voitureresevation.exception;

import com.example.voitureresevation.zutils.exception.ApiError;
import com.example.voitureresevation.zutils.validators.ValidateResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundExceptionExceptions(NotFoundException e) {
        var error = new ApiError(e.getMessage(), e.getStatus());
        return ResponseEntity
                .status(error.getStatus())
                .body(error);
    }

    @ExceptionHandler(ValidatorException.class)
    public ResponseEntity<List<ValidateResult<?>>> handleValidatorException(ValidatorException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(e.getResults());
    }
}
