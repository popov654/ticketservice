package com.example.ticketsale.controller;

import com.example.ticketsale.dto.FillBalanceDto;
import com.example.ticketsale.dto.ResponseSuccess;
import com.example.ticketsale.model.Client;
import com.example.ticketsale.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping({ "", "/" })
    public List<Client> getAll() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client get(@PathVariable("id") Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping("/{id}/fill-balance")
    public Object fill(@PathVariable("id") Long clientId, @RequestBody FillBalanceDto data) {
        clientService.fillBalance(clientId, data.getAmount());
        return new ResponseSuccess("ok");
    }
}
