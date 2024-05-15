package com.example.ticketsale.controller;

import com.example.ticketsale.dto.ResponseError;
import com.example.ticketsale.dto.ResponseSuccess;
import com.example.ticketsale.model.Ticket;
import com.example.ticketsale.service.TicketService;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/ticket/{id}")
    public Ticket get(@PathVariable("id") UUID id) {
        return ticketService.getTicketById(id);
    }

    @GetMapping("/ticket/{eventId}/generate")
    public Ticket create(@PathVariable("eventId") long eventId, @RequestParam("cost") @DefaultValue("500") String cost) {
        return ticketService.createTicket(eventId, new BigDecimal(cost));
    }

    @GetMapping("/tickets/event/{eventId}")
    public List<Ticket> getByEvent(@PathVariable("eventId") long eventId) {
        return ticketService.getByEvent(eventId);
    }

    @GetMapping("/sale/{clientId}/{ticketId}")
    public Object sale(@PathVariable("clientId") long clientId, @PathVariable("ticketId") UUID ticketId) {
        try {
            ticketService.saleTicket(clientId, ticketId);
            return new ResponseSuccess("ok");
        } catch (Exception e) {
            return new ResponseError(e.getMessage());
        }
    }

    @GetMapping("/redeem/{ticketId}")
    public Object redeem(@PathVariable("ticketId") UUID ticketId) {
        try {
            ticketService.redeemTicket(ticketId);
            return new ResponseSuccess("ok");
        } catch (Exception e) {
            return new ResponseError(e.getMessage());
        }
    }
}
