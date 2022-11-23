package com.aurum.aurumapp.exceptions;


public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException(String msg){
        super(msg);
    }
}
