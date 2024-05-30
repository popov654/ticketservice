package com.example.ticketsale.controller;

import com.example.ticketsale.dto.EventDto;
import com.example.ticketsale.model.Event;
import com.example.ticketsale.service.EventService;
import com.example.ticketsale.service.impl.DefaultEventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(DefaultEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping({ "", "/" })
    public List<Event> getAll() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event get(@PathVariable("id") Long id) {
        return eventService.getEventById(id);
    }

    @PostMapping("/new")
    public Event create(@RequestBody EventDto eventDto) {
        return eventService.createEvent(eventDto.getName(), eventDto.getDate());
    }
}
