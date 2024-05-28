package com.example.ticketsale.controller;

import com.example.ticketsale.dto.ResponseError;
import com.example.ticketsale.dto.ResponseSuccess;
import com.example.ticketsale.model.Ticket;
import com.example.ticketsale.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/ticket/{id}")
    public Ticket get(@PathVariable("id") UUID id) {
        return ticketService.getTicketById(id);
    }

    @GetMapping("/ticket/{event_id}/new")
    public Ticket create(@PathVariable("event_id") long eventId, @RequestParam("cost") @DefaultValue("500") String cost) {
        return ticketService.createTicket(eventId, new BigDecimal(cost));
    }

    @GetMapping("/tickets/event/{event_id}")
    public List<Ticket> getByEvent(@PathVariable("event_id") long eventId) {
        return ticketService.getTicketByEventId(eventId);
    }

    @GetMapping("/sale/{ticket_id}")
    public ResponseEntity<Object> sale(@RequestParam("client_id") long clientId, @PathVariable("ticket_id") UUID ticketId) {
        try {
            ticketService.saleTicket(clientId, ticketId);
            return new ResponseEntity<Object>(new ResponseSuccess("ok"), HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<Object>(new ResponseError(e.getMessage()), HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping("/redeem/{ticket_id}")
    public ResponseEntity<Object> redeem(@PathVariable("ticket_id") UUID ticketId) {
        try {
            ticketService.redeemTicket(ticketId);
            return new ResponseEntity<Object>(new ResponseSuccess("ok"), HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<Object>(new ResponseError(e.getMessage()), HttpStatusCode.valueOf(500));
        }
    }
}
