package com.example.ticketsale.dto.mapper;

import com.example.ticketsale.dto.EventDto;
import com.example.ticketsale.model.Event;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDto eventToEventDto(Event event);
    Event eventDtoToEvent(EventDto eventDto);
    List<EventDto> mapEventsToEventDtos(List<Event> events);
    List<Event> mapEventDtosToEvents(List<EventDto> eventDtos);
}
