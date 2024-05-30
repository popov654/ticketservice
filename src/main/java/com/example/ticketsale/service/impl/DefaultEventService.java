package com.example.ticketsale.service.impl;

import com.example.ticketsale.model.Event;
import com.example.ticketsale.repository.EventRepository;
import com.example.ticketsale.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DefaultEventService implements EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(String name, LocalDateTime date) {
        Event event = new Event();
        event.setName(name);
        event.setDate(date);
        eventRepository.save(event);
        return event;
    }

    public Event getEventById(Long id) {
        return eventRepository.getById(id);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
