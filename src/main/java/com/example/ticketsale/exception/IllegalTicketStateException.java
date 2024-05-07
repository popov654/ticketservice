package com.example.ticketsale.exception;

public class IllegalTicketStateException extends Exception {
    public IllegalTicketStateException() {
        super("Illegal ticket state");
    }
}
