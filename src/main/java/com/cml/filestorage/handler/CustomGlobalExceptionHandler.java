package com.cml.filestorage.handler;

import com.cml.filestorage.exception.FileDoesNotExistsException;
import com.cml.filestorage.exception.InvalidInputException;
import com.cml.filestorage.exception.TagDoesNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FileDoesNotExistsException.class)
    public ResponseEntity<CustomErrorResponse> springHandleNotFound(Exception ex){
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setError(ex.getMessage());
        errors.setSuccess("false");
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidInputException.class, TagDoesNotExistsException.class})
    public ResponseEntity<CustomErrorResponse> springHandleBadRequest(Exception ex){
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setError(ex.getMessage());
        errors.setSuccess("false");
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
