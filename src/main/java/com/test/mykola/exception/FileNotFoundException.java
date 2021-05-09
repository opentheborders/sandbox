package com.test.mykola.exception;

import static java.lang.String.format;

public class FileNotFoundException extends RuntimeException {

    public FileNotFoundException(String filename) {
        super(format("File=%s not found", filename));
    }

    public FileNotFoundException(String filename, Throwable cause) {
        super(format("File=%s not found", filename), cause);
    }
}
