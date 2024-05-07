package com.example.ticketsale.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="events")
@Getter @Setter
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Basic
    private long id;
    private String name;
    private LocalDateTime date;

    @OneToMany(mappedBy = "event", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Ticket> tickets;
}
