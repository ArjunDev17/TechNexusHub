package com.codeneeti.technexushub.exceptions;

public class BadApiRequest extends RuntimeException{
    String msg;

    public BadApiRequest() {
        super("Bad Request !!");
    }

    public BadApiRequest(String message) {
        super(message);
    }
}
