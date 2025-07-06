package com.example.CustomExceptions;

public class DuplicateGameException extends Exception {
    public DuplicateGameException(String message) {
        super(message);
    }

}
