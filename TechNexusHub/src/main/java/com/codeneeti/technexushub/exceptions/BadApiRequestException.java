package com.codeneeti.technexushub.exceptions;

public class BadApiRequestException extends RuntimeException{
    String msg;

    public BadApiRequestException() {
        super("Bad Request !!");
    }

    public BadApiRequestException(String message) {
        super(message);
    }
}
