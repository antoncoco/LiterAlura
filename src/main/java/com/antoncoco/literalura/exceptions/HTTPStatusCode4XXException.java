package com.antoncoco.literalura.exceptions;

public class HTTPStatusCode4XXException extends RuntimeException {
    public HTTPStatusCode4XXException(String message) {
        super(message);
    }
}
