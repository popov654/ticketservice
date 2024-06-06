package com.example.ticketsale.controller;

import com.example.ticketsale.model.Ticket;
import com.example.ticketsale.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tickets")
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/show/{id}")
    public Ticket get(@PathVariable("id") UUID id) {
        return ticketService.getTicketById(id);
    }

    @GetMapping("/event/{event_id}/new")
    public Ticket create(@PathVariable("event_id") long eventId, @RequestParam("cost") @DefaultValue("500") String cost) {
        return ticketService.createTicket(eventId, new BigDecimal(cost));
    }

    @GetMapping("/event/{event_id}")
    public List<Ticket> getByEvent(@PathVariable("event_id") long eventId) {
        return ticketService.getTicketByEventId(eventId);
    }

    @GetMapping("/sale/{ticket_id}")
    public void sale(@RequestParam("client_id") long clientId, @PathVariable("ticket_id") UUID ticketId) {
        ticketService.saleTicket(clientId, ticketId);
    }

    @GetMapping("/redeem/{ticket_id}")
    public void redeem(@PathVariable("ticket_id") UUID ticketId) {
        ticketService.redeemTicket(ticketId);
    }
}
