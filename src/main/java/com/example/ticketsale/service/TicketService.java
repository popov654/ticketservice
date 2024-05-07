package com.example.ticketsale.service;

import com.example.ticketsale.exception.IllegalTicketStateException;
import com.example.ticketsale.exception.LowBalanceException;
import com.example.ticketsale.model.Client;
import com.example.ticketsale.model.Event;
import com.example.ticketsale.model.Ticket;
import com.example.ticketsale.model.TicketStatus;
import com.example.ticketsale.repository.ClientRepository;
import com.example.ticketsale.repository.EventRepository;
import com.example.ticketsale.repository.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    public final static BigDecimal TAX_RATE = new BigDecimal("0.2");

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EventRepository eventRepository;

    public Ticket getById(UUID id) {
        return ticketRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
    }

    @Transactional
    public void saleTicket(Long clientId, UUID ticketId) throws LowBalanceException, IllegalTicketStateException {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new EntityNotFoundException("Client not found"));
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
        if (ticket.getStatus() != TicketStatus.ON_SALE) {
            throw new IllegalTicketStateException();
        }
        client.setBalance(client.getBalance().subtract(ticket.getCost().multiply(TAX_RATE.add(new BigDecimal("1")))));
        if (client.getBalance().compareTo(new BigDecimal(0)) < 0) {
            throw new LowBalanceException("Balance is low");
        }
        ticket.setOwner(client);
    }

    public Ticket createTicket(Long eventId, BigDecimal cost) {
        Event event = eventRepository.findById(eventId).orElseThrow(EntityNotFoundException::new);
        Ticket ticket = new Ticket();
        ticket.setId(UUID.randomUUID());
        ticket.setEvent(event);
        ticket.setCost(cost);
        ticketRepository.save(ticket);
        return ticket;
    }

    public List<Ticket> getByEvent(long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(EntityNotFoundException::new);
        return ticketRepository.findByEvent(event);
    }

    public void redeemTicket(UUID ticketId) throws IllegalTicketStateException {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
        if (ticket.getOwner() == null) {
            throw new IllegalTicketStateException();
        }
        ticket.setUsed(true);
        ticketRepository.save(ticket);
    }

}
