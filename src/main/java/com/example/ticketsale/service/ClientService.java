package com.example.ticketsale.service;

import com.example.ticketsale.model.Client;
import com.example.ticketsale.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Client get(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found"));
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public void fillBalance(Long clientId, BigDecimal amount) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new EntityNotFoundException("Client not found"));
        client.setBalance(client.getBalance().add(amount));
        clientRepository.save(client);
    }
}
