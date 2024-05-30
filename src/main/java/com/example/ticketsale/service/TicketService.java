package com.example.ticketsale.service;

import com.example.ticketsale.exception.IllegalTicketStateException;
import com.example.ticketsale.exception.LowBalanceException;
import com.example.ticketsale.model.Ticket;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface TicketService {

    public Ticket getTicketById(UUID id);

    public List<Ticket> getTicketByEventId(long eventId);

    public void saleTicket(Long clientId, UUID ticketId) throws LowBalanceException, IllegalTicketStateException;

    public Ticket createTicket(Long eventId, BigDecimal cost);

    public void redeemTicket(UUID ticketId) throws IllegalTicketStateException;

}
