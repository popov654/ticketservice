package com.example.ticketsale.controller;

import com.example.ticketsale.dto.Response;
import com.example.ticketsale.model.Ticket;
import com.example.ticketsale.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Response> sale(@RequestParam("client_id") long clientId, @PathVariable("ticket_id") UUID ticketId) {
        try {
            ticketService.saleTicket(clientId, ticketId);
            return new ResponseEntity<Response>(Response.success("ok"), HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<Response>(Response.error(e.getMessage()), HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping("/redeem/{ticket_id}")
    public ResponseEntity<Response> redeem(@PathVariable("ticket_id") UUID ticketId) {
        try {
            ticketService.redeemTicket(ticketId);
            return new ResponseEntity<Response>(Response.success("ok"), HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<Response>(Response.error(e.getMessage()), HttpStatusCode.valueOf(500));
        }
    }
}
