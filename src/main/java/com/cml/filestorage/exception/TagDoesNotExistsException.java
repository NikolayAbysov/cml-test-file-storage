package com.cml.filestorage.exception;

public class TagDoesNotExistsException extends RuntimeException {
    public TagDoesNotExistsException(String errorMessage) {
        super(errorMessage);
    }
}
