package com.example.ticketsale.service.impl;

import com.example.ticketsale.exception.IllegalTicketStateException;
import com.example.ticketsale.exception.LowBalanceException;
import com.example.ticketsale.model.Client;
import com.example.ticketsale.model.Event;
import com.example.ticketsale.model.Ticket;
import com.example.ticketsale.model.TicketStatus;
import com.example.ticketsale.repository.ClientRepository;
import com.example.ticketsale.repository.EventRepository;
import com.example.ticketsale.repository.TicketRepository;
import com.example.ticketsale.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class DefaultTicketService implements TicketService {

    public final static BigDecimal TAX_RATE = new BigDecimal("0.2");

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EventRepository eventRepository;

    public Ticket getTicketById(UUID id) {
        return ticketRepository.getById(id);
    }

    public List<Ticket> getTicketByEventId(long eventId) {
        Event event = eventRepository.getById(eventId);
        return ticketRepository.findByEvent(event);
    }

    @Transactional
    public void saleTicket(Long clientId, UUID ticketId) throws LowBalanceException, IllegalTicketStateException {
        Client client = clientRepository.getById(clientId);
        Ticket ticket = ticketRepository.getById(ticketId);
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
        Event event = eventRepository.getById(eventId);
        Ticket ticket = new Ticket();
        ticket.setId(UUID.randomUUID());
        ticket.setEvent(event);
        ticket.setCost(cost);
        ticketRepository.save(ticket);
        return ticket;
    }

    public void redeemTicket(UUID ticketId) throws IllegalTicketStateException {
        Ticket ticket = ticketRepository.getById(ticketId);
        if (ticket.getOwner() == null) {
            throw new IllegalTicketStateException();
        }
        ticket.setUsed(true);
        ticketRepository.save(ticket);
    }

}
