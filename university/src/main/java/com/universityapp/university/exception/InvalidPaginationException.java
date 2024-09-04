package com.universityapp.university.exception;

public class InvalidPaginationException extends RuntimeException {
    public InvalidPaginationException(String message) {
        super(message);
    }
}