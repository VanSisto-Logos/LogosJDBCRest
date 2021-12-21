package com.vansisto.exception;

public class PasswordsDontMatchException extends Throwable {
    public PasswordsDontMatchException(){
        super("Passwords didn't match.");
    }
}
