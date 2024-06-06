package com.example.ticketsale.exception;

public class LowBalanceException extends RuntimeException {
    public LowBalanceException() {}

    public LowBalanceException(String message) {
        super(message);
    }
}
