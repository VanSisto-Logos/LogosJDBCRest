package com.vansisto.exception;

import lombok.extern.log4j.Log4j;

@Log4j
public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String email) {
        super("User with email " + email + " not found.");
        log.error("User with email : \"" + email + "\" doesn't exist");
    }

    public UserNotFoundException(int id) {
        super("User with id " + id + " not found.");
        log.error("User with id : \"" + id + "\" doesn't exist");
    }
}
