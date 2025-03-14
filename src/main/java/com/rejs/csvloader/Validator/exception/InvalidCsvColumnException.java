package com.rejs.csvloader.Validator.exception;

import lombok.Getter;

@Getter
public class InvalidCsvColumnException extends RuntimeException{

    public InvalidCsvColumnException(int columnIndex, String message, Throwable cause) {
        super(columnIndex + " column is invalid : " + message, cause);
    }

    public InvalidCsvColumnException() {
    }

    public InvalidCsvColumnException(Throwable cause) {
        super(cause);
    }


    public InvalidCsvColumnException(String message) {
        super(message);
    }
}
