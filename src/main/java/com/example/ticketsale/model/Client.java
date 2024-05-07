package com.example.ticketsale.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="clients")
@Getter @Setter
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Basic
    private long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private BigDecimal balance;
}
