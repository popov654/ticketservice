package com.example.ticketsale.exception;

public class IllegalTicketStateException extends RuntimeException {
    public IllegalTicketStateException() {
        super("Illegal ticket state");
    }
}
