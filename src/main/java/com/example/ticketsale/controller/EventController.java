package com.example.ticketsale.controller;

import com.example.ticketsale.dto.EventDto;
import com.example.ticketsale.dto.mapper.EventMapper;
import com.example.ticketsale.model.Event;
import com.example.ticketsale.service.EventService;
import com.example.ticketsale.service.impl.DefaultEventService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {

    private final EventService eventService;

    private final EventMapper eventMapper;

    @GetMapping({ "", "/" })
    public List<EventDto> getAll() {
        return eventMapper.mapEventsToEventDtos(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public EventDto get(@PathVariable("id") Long id) {
        return eventMapper.eventToEventDto(eventService.getEventById(id));
    }

    @PostMapping("/new")
    public Event create(@RequestBody EventDto eventDto) {
        return eventService.createEvent(eventDto.getName(), eventDto.getDate());
    }
}
