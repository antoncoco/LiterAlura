package com.antoncoco.literalura.exceptions;

public class UnexpectedMenuOptionException extends RuntimeException {
    public UnexpectedMenuOptionException(String message) {
        super(message);
    }
}
