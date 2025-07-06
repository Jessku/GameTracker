package com.example.CustomExceptions;

public class DuplicateUsernameException extends IllegalArgumentException {
    public DuplicateUsernameException(String message) {
        super(message);
    }
}