package com.example.ticketsale.service;

import com.example.ticketsale.model.Client;

import java.math.BigDecimal;
import java.util.List;

public interface ClientService {

    public Client getClientById(Long id);

    public List<Client> getAllClients();

    public void fillBalance(Long clientId, BigDecimal amount);

}
