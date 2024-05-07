package com.example.ticketsale.repository;

import com.example.ticketsale.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
