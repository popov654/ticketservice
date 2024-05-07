package com.example.ticketsale.repository;

import com.example.ticketsale.model.Event;
import com.example.ticketsale.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    List<Ticket> findByEvent(Event event);
}
