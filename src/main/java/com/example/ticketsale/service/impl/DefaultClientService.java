package com.example.ticketsale.service.impl;

import com.example.ticketsale.model.Client;
import com.example.ticketsale.repository.ClientRepository;
import com.example.ticketsale.service.ClientService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DefaultClientService implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found"));
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Transactional
    public void fillBalance(Long clientId, BigDecimal amount) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new EntityNotFoundException("Client not found"));
        client.setBalance(client.getBalance().add(amount));
        clientRepository.save(client);
    }
}
