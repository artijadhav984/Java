package com.bookshop.restservice.infrastructure.exception;

/**
 * @author Arti
 * @version 1.0
 */
public class ISBNNotFoundException extends Exception {

    private String message;

    public ISBNNotFoundException() {
        this.message = "ISBN (Id) not found";
    }

    public ISBNNotFoundException(String message) {
        this.message = message;
    }
}
