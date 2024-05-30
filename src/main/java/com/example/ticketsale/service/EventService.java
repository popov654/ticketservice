package com.example.ticketsale.service;

import com.example.ticketsale.model.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    public Event getEventById(Long id);

    public List<Event> getAllEvents();

    public Event createEvent(String name, LocalDateTime date);

}
