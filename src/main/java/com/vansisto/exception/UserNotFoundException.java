package com.vansisto.exception;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String email) {
        super("User with email " + email + " not found.");
    }
}
