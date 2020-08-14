package com.cml.filestorage.exception;

public class FileDoesNotExistsException extends RuntimeException {
    public FileDoesNotExistsException(String errorMessage) {
        super(errorMessage);
    }
}
