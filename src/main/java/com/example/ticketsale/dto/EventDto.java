package com.example.ticketsale.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class EventDto {
    private long id;
    private String name;
    private LocalDateTime date;
}
