package com.example.ticketsale.exception;

public class LowBalanceException extends Exception {
    public LowBalanceException() {}

    public LowBalanceException(String message) {
        super(message);
    }
}
