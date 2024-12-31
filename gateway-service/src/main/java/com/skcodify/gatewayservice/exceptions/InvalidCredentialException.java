package com.skcodify.gatewayservice.exceptions;

public class InvalidCredentialException extends Throwable {

    private String message;
    public InvalidCredentialException(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
