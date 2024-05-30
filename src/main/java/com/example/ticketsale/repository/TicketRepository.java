package com.example.ticketsale.repository;

import com.example.ticketsale.model.Event;
import com.example.ticketsale.model.Ticket;

import java.util.List;
import java.util.UUID;

public interface TicketRepository extends BasicRepository<Ticket, UUID> {
    List<Ticket> findByEvent(Event event);
}
