package com.kuznetsov.miniStock.exception;

public class DaoException extends RuntimeException{
    public DaoException(String message) {
        super(message);
    }
}
