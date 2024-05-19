package com.example.ticketsale.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class ClientWithBalanceDto {
    private long id;
    private String username;
    private String name;
    private String email;
    private BigDecimal balance;
}
