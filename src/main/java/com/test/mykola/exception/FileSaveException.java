package com.test.mykola.exception;

import static java.lang.String.format;

public class FileSaveException extends RuntimeException {

    public FileSaveException(String message) {
        super(format("Could not store the file. Error: %s", message));
    }
}

