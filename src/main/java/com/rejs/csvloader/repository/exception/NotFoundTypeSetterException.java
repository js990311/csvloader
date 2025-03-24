package com.rejs.csvloader.repository.exception;

public class NotFoundTypeSetterException extends RuntimeException{
    public NotFoundTypeSetterException(String type) {
        super("cannot found setter type: " + type);
    }
}
