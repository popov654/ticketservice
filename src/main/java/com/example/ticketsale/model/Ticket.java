package com.example.ticketsale.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Entity
@Table(name="tickets")
@Getter @Setter
public class Ticket {
    @Id
    private UUID id;
    private BigDecimal cost;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Event event;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "owner_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Client owner;

    @JsonIgnore
    private boolean isUsed;

    public TicketStatus getStatus() {
        if (owner != null && isUsed) {
            return TicketStatus.USED;
        }
        if (event.getDate().toEpochSecond(ZoneOffset.UTC) < LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)) {
            return TicketStatus.EXPIRED;
        }
        if (owner != null) {
            return TicketStatus.ACTIVE;
        }
        return TicketStatus.ON_SALE;
    }
}
