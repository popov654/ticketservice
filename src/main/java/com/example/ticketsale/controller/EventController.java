package com.example.ticketsale.controller;

import com.example.ticketsale.dto.EventDto;
import com.example.ticketsale.model.Event;
import com.example.ticketsale.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping({ "", "/" })
    public List<Event> getAll() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event get(@PathVariable("id") Long id) {
        return eventService.getEventById(id);
    }

    @PostMapping("/new")
    public Event newEvent(@RequestBody EventDto eventDto) {
        return eventService.createEvent(eventDto.getName(), eventDto.getDate());
    }
}
