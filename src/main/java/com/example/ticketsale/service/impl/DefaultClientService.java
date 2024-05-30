package com.example.ticketsale.service.impl;

import com.example.ticketsale.model.Client;
import com.example.ticketsale.repository.ClientRepository;
import com.example.ticketsale.service.ClientService;
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
        return clientRepository.getById(id);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Transactional
    public void fillBalance(Long clientId, BigDecimal amount) {
        Client client = clientRepository.getById(clientId);
        client.setBalance(client.getBalance().add(amount));
        clientRepository.save(client);
    }
}
